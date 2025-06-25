package com.example.demo.controller.dto;


import java.time.LocalDate;

public record CreateUserDto(String username, String password, String ime, String prezime,String email, LocalDate rodjendan) {
}
