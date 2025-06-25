package com.example.reservation_service.mapper;

import com.example.reservation_service.controller.dto.CreateReservationDto;
import com.example.reservation_service.domain.Rezervacija;
import com.example.reservation_service.domain.dto.RezervacijaDto;
import com.example.reservation_service.repository.RezervacijaRepository;
import com.example.reservation_service.repository.StoRepository;
import org.springframework.stereotype.Component;

@Component
public class RezervacijaMapper {
    private final RezervacijaRepository rezervacijaRepository;
    private final StoRepository stoRepository;

    public RezervacijaMapper(RezervacijaRepository rezervacijaRepository, StoRepository stoRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.stoRepository = stoRepository;
    }

    public RezervacijaDto toDto(Rezervacija rezervacija) {
        return new RezervacijaDto(
                rezervacija.getId(),
                rezervacija.getDatum(),
                rezervacija.getVremeTrajanja(),
                rezervacija.getKorisnikUsername(),
                rezervacija.getDostupno(),
                rezervacija.getSto() != null ? rezervacija.getSto().getId() : null
        );
    }

    public Rezervacija fromDto(CreateReservationDto dto) {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatum(dto.datum());
        rezervacija.setVremeTrajanja(dto.vremeTrajanja());
        rezervacija.setKorisnikUsername(dto.korisnikUsername());
        rezervacija.setDostupno(dto.dostupno());
        var sto = stoRepository.findById(dto.stoId());
        sto.ifPresent(rezervacija::setSto);
        return rezervacija;
    }
}
