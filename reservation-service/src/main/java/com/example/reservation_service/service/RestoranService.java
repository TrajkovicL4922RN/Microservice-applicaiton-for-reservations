package com.example.reservation_service.service;

import com.example.reservation_service.domain.dto.RestoranDto;
import com.example.reservation_service.domain.dto.UpdateRestoranDto;
import jakarta.validation.Valid;

public interface RestoranService{
    RestoranDto update(Long id, @Valid UpdateRestoranDto restoranDto);
}
