package com.example.reservation_service.domain.dto;

import java.util.List;

public record RestoranDto(Long id,String ime, String adresa, String opis, String radnoVreme, List<String> kuhinje) {
}
