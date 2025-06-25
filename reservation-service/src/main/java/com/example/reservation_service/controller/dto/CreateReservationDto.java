package com.example.reservation_service.controller.dto;

import java.time.LocalDate;

public record CreateReservationDto(LocalDate datum, Integer vremeTrajanja, String korisnikUsername, Boolean dostupno, Long stoId) {
}
