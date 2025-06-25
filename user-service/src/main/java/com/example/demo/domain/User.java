package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Entity
@Table(name = "korisnik")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean active;

    @Column(name = "br_rezervacija")
    private Integer brRez;

    @Column(name="naziv_restorana")
    private String nazivRestorana;

    @Column(name="datum_zaposljavanja")
    private LocalDate datumZaposljavanja;

    @Email
    private String email;

    private String ime;

    private String prezime;

    private LocalDate rodjendan;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getBrRez() {
        return brRez;
    }

    public void setBrRez(Integer brRez) {
        this.brRez = brRez;
    }

    public LocalDate getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(LocalDate datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getRodjendan() {
        return rodjendan;
    }

    public void setRodjendan(LocalDate rodjendan) {
        this.rodjendan = rodjendan;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
