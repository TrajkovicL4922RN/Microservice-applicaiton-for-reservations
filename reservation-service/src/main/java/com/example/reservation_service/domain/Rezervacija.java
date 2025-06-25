package com.example.reservation_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate datum;

    @Column(name = "vreme_trajanja")
    private Integer vremeTrajanja;

    @Column
    private String korisnikUsername;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sto_id", nullable = false)
    private Sto sto;

    @Column(name = "dostupno", nullable = false)
    private Boolean dostupno = true;

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Integer getVremeTrajanja() {
        return vremeTrajanja;
    }

    public void setVremeTrajanja(Integer vremeTrajanja) {
        this.vremeTrajanja = vremeTrajanja;
    }

    public String getKorisnikUsername() {
        return korisnikUsername;
    }

    public void setKorisnikUsername(String korisnikUsername) {
        this.korisnikUsername = korisnikUsername;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    public Boolean getDostupno() {
        return dostupno;
    }

    public void setDostupno(Boolean dostupno) {
        this.dostupno = dostupno;
    }
}

