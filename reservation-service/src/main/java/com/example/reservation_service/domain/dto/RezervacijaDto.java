package com.example.reservation_service.domain.dto;

import jdk.jfr.DataAmount;

import java.time.LocalDate;

public record RezervacijaDto(Long id, LocalDate datum, Integer vremeTrajanja, String korisnikUsername, Boolean dostupno, Long stoId) {
}
