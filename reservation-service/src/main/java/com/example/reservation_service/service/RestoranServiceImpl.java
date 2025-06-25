package com.example.reservation_service.service;

import com.example.reservation_service.controller.dto.CreateRestoranDto;
import com.example.reservation_service.domain.dto.RestoranDto;
import com.example.reservation_service.domain.Restoran;
import com.example.reservation_service.domain.dto.UpdateRestoranDto;
import com.example.reservation_service.mapper.RestoranMapper;
import com.example.reservation_service.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestoranServiceImpl implements RestoranService {

    private final RestoranRepository restoranRepository;
    private final RestoranMapper restoranMapper;

    @Autowired
    public RestoranServiceImpl(RestoranRepository restoranRepository, RestoranMapper restoranMapper) {
        this.restoranRepository = restoranRepository;
        this.restoranMapper = restoranMapper;
    }

    public RestoranDto saveRestoran(CreateRestoranDto createRestoranDto) {
        Restoran restoran = restoranMapper.fromDto(createRestoranDto);
        Restoran savedRestoran = restoranRepository.save(restoran);
        return restoranMapper.toDto(savedRestoran);
    }

    public Optional<RestoranDto> findRestoranById(Long id) {
        return restoranRepository.findById(id).map(restoranMapper::toDto);
    }


    @Override
    public RestoranDto update(Long id, UpdateRestoranDto updateRestoranDto) {
        Restoran restoran = restoranRepository.findRestoranById(id);
        if (!(updateRestoranDto.getIme()==null) && !(updateRestoranDto.getIme().isEmpty())){
            restoran.setIme(updateRestoranDto.getIme());
        }
        if (!(updateRestoranDto.getAdresa()==null) && !(updateRestoranDto.getAdresa().isEmpty())){
            restoran.setAdresa(updateRestoranDto.getAdresa());

        }
        if (!(updateRestoranDto.getKuhinje()==null) && !(updateRestoranDto.getKuhinje().isEmpty())){
            restoran.setKuhinje(updateRestoranDto.getKuhinje());
        }
        if (!(updateRestoranDto.getOpis()==null) && !(updateRestoranDto.getOpis().equalsIgnoreCase(""))){
            restoran.setOpis(updateRestoranDto.getOpis());
        }
        if (!(updateRestoranDto.getRadnoVreme()==null) && !(updateRestoranDto.getRadnoVreme().isEmpty())){
            restoran.setRadnoVreme(updateRestoranDto.getRadnoVreme());
        }

        restoranRepository.save(restoran);
        return restoranMapper.toDto(restoran);
    }
}
