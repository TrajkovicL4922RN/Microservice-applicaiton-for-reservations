package com.example.demo.controller.dto;

import java.time.LocalDate;

public record CreateManagerDto(String username, String password, String ime, String prezime, LocalDate datumZaposljavanja
        , String nazivRestorana, String email, LocalDate rodjendan) {
}
