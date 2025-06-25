package com.example.reservation_service.service;

import com.example.reservation_service.controller.dto.CreateReservationDto;
import com.example.reservation_service.domain.Rezervacija;
import com.example.reservation_service.domain.Sto;
import com.example.reservation_service.domain.dto.RezervacijaDto;
import com.example.reservation_service.domain.dto.TransferDto;
import com.example.reservation_service.helper.MessageHelper;
import com.example.reservation_service.mapper.RezervacijaMapper;
import com.example.reservation_service.repository.RezervacijaRepository;
import com.example.reservation_service.repository.StoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.http.HttpMethod;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezervacijaService {
    @PersistenceContext
    private EntityManager entityManager;
    private final RestTemplate userServiceApiClient;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;


    private final RezervacijaRepository rezervacijaRepository;
    private final RezervacijaMapper rezervacijaMapper;
    private final StoRepository stoRepository;
    private static final String RESERVATION_SERVICE_URL = "http://localhost:8080/api/users";

    public Integer getBrojRezervacija(String username) {
        String url = RESERVATION_SERVICE_URL + "/count?username=" + username;
        return userServiceApiClient.getForObject(url, Integer.class);
    }


    public RezervacijaService(RestTemplate userServiceApiClient, MessageHelper messageHelper, RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper, StoRepository stoRepository) {
        this.userServiceApiClient = userServiceApiClient;
        this.jmsTemplate = new JmsTemplate();
        this.messageHelper = messageHelper;
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.stoRepository = stoRepository;
        //this.restTemplate = restTemplate;
    }

    @Transactional
    public RezervacijaDto dodajRezervaciju(CreateReservationDto rezervacijaDto) {
        Rezervacija rezervacija = rezervacijaMapper.fromDto(rezervacijaDto);
        Rezervacija sacuvanaRezervacija = rezervacijaRepository.save(rezervacija);
/*        TransferDto transferDto = new TransferDto("plusingg@gmail.com","SCHED_TR", new HashMap<>(),
                "trajkovic");
        jmsTemplate.convertAndSend("send_mail_destination",
                messageHelper.createTextMessage(transferDto));*/
        System.out.println("Spas");
        return rezervacijaMapper.toDto(sacuvanaRezervacija);
    }

    public void updateBrojRezervacija(String username) {
        String endpoint = "/users/update-reservations?username=" + username;
        userServiceApiClient.exchange(endpoint,HttpMethod.PUT,null,String.class);
    }

    public List<RezervacijaDto> filtrirajSlobodneTermine(
            String lokacija,
            String tipKuhinje,
            Integer brojOsoba,
            LocalDate datum,
            Integer vreme
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rezervacija> query = cb.createQuery(Rezervacija.class);
        Root<Rezervacija> rezervacijaRoot = query.from(Rezervacija.class);
        Join<Rezervacija, Sto> stoJoin = rezervacijaRoot.join("sto");

        List<Predicate> predicates = new ArrayList<>();

        // Filtriranje po lokaciji
        if (lokacija != null) {
            predicates.add(cb.equal(stoJoin.get("restoran").get("adresa"), lokacija));
        }

        // Filtriranje po tipu kuhinje
        if (tipKuhinje != null) {
            Join<Object, Object> kuhinjeJoin = stoJoin.join("restoran").join("kuhinje");
            predicates.add(cb.equal(kuhinjeJoin.get("naziv"), tipKuhinje));
        }

        // Filtriranje po broju osoba
        if (brojOsoba != null) {
            predicates.add(cb.greaterThanOrEqualTo(stoJoin.get("brojMesta"), brojOsoba));
        }

        // Filtriranje po dostupnosti
        predicates.add(cb.isTrue(rezervacijaRoot.get("dostupno")));

        // Filtriranje po datumu i vremenu
        if (datum != null) {
            predicates.add(cb.equal(rezervacijaRoot.get("datum"), datum));

        }
        if (vreme != null){
            predicates.add(cb.equal(rezervacijaRoot.get("vremeTrajanja"), vreme));
        }

        // Glavni upit
        query.select(rezervacijaRoot).where(predicates.toArray(new Predicate[0]));
        List<Rezervacija> slobodneRezervacije = entityManager.createQuery(query).getResultList();

        // Mapiranje slobodnih rezervacija u DTO
        return slobodneRezervacije.stream()
                .map(rezervacija -> new RezervacijaDto(
                        rezervacija.getId(),
                        rezervacija.getDatum(),
                        rezervacija.getVremeTrajanja(),
                        rezervacija.getKorisnikUsername(),
                        rezervacija.getDostupno(),
                        rezervacija.getSto().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<RezervacijaDto> filtrirajPoBrojuMesta(Integer brojMesta, LocalDate datum, Integer vreme) {
        // Pronalazimo slobodne stolove
        List<Sto> slobodniStolovi = stoRepository.findSlobodniStolovi(datum, vreme, brojMesta);

        // Mapiramo slobodne stolove u DTO
        return slobodniStolovi.stream()
                .map(sto -> new RezervacijaDto(
                        null, // id je null jer ovo nije rezervacija već slobodan termin
                        datum,
                        vreme,
                        null, // korisnikUsername je null jer nema korisnika za slobodan termin
                        true, // dostupno je true jer je ovo slobodan termin
                        sto.getId()
                ))
                .toList();
    }

    public void otkazivanjeManager(Long id, Boolean dostupno){
        var rezervacija = rezervacijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        rezervacija.setDostupno(dostupno);
        rezervacija.setKorisnikUsername(null);
        rezervacijaRepository.save(rezervacija);

    }

    public void smanjiBrojRezervacija(String username) {
        String endpoint = "/users/smanji-reservations?username=" + username;
        userServiceApiClient.exchange(endpoint,HttpMethod.PUT,null,String.class);
    }




    public void otkazivanjeKlijent(Long id, String username){

        var rezervacija = rezervacijaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (rezervacija.getKorisnikUsername().equals(username)) {
            rezervacija.setDostupno(true);
            rezervacija.setKorisnikUsername(null);
            rezervacijaRepository.save(rezervacija);
            smanjiBrojRezervacija(username);
        }


    }

    @Transactional
    public void zakazi(Long id, String username){
        var rezervacija = rezervacijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (rezervacija.getDostupno()) {
            System.out.println("USAO");
            rezervacija.setDostupno(false);
            rezervacija.setKorisnikUsername(username);
            rezervacijaRepository.save(rezervacija);
            updateBrojRezervacija(username);
            /*TransferDto transferDto = new TransferDto("plusingg@gmail.com","SCHED_TR", new HashMap<>(),
                    "trajkovic");
            jmsTemplate.convertAndSend("send_mail_destination",
                    messageHelper.createTextMessage(transferDto));*/
        }

    }





}
