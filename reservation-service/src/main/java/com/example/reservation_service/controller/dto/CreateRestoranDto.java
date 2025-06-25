package com.example.reservation_service.controller.dto;

import java.util.List;

public record CreateRestoranDto(String ime, String adresa, String opis, String radnoVreme, List<Long> kuhinjeIds) {
}
