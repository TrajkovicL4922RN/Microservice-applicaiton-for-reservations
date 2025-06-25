package com.example.reservation_service.mapper;

import com.example.reservation_service.controller.dto.CreateRestoranDto;
import com.example.reservation_service.domain.dto.RestoranDto;
import com.example.reservation_service.domain.Kuhinja;
import com.example.reservation_service.domain.Restoran;
import com.example.reservation_service.repository.KuhinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestoranMapper {

    private final KuhinjaRepository kuhinjaRepository;

    @Autowired
    public RestoranMapper(KuhinjaRepository kuhinjaRepository) {
        this.kuhinjaRepository = kuhinjaRepository;
    }

    public Restoran fromDto(CreateRestoranDto createRestoranDto) {
        Restoran restoran = new Restoran();
        restoran.setIme(createRestoranDto.ime());
        restoran.setAdresa(createRestoranDto.adresa());
        restoran.setOpis(createRestoranDto.opis());
        restoran.setRadnoVreme(createRestoranDto.radnoVreme());

        List<Kuhinja> kuhinje = kuhinjaRepository.findAllById(createRestoranDto.kuhinjeIds());
        restoran.setKuhinje(kuhinje);

        return restoran;
    }

    public RestoranDto toDto(Restoran restoran) {
        List<String> kuhinjeNazivi = restoran.getKuhinje().stream()
                .map(Kuhinja::getNaziv)
                .collect(Collectors.toList());

        return new RestoranDto(
                restoran.getId(),
                restoran.getIme(),
                restoran.getAdresa(),
                restoran.getOpis(),
                restoran.getRadnoVreme(),
                kuhinjeNazivi
        );
    }
}
