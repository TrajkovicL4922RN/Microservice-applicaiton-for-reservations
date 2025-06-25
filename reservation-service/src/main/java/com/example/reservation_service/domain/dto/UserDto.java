package com.example.reservation_service.domain.dto;

import java.time.LocalDate;

public record UserDto(String ime, String prezime, String email, String username, String password,String datumRodjenja) {
}
