package com.example.demo.domain.dto;

import java.time.LocalDate;

public record UserDto(Long id, String username, String password, String ime, String prezime, String email, Integer brRez,
                      LocalDate rodjendan,String nazivRestorana, LocalDate datumZaposljavanja, Boolean active) {
}
