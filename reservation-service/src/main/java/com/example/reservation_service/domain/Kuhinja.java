package com.example.reservation_service.domain;

import com.example.reservation_service.domain.Restoran;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "kuhinja")
public class Kuhinja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @ManyToMany(mappedBy = "kuhinje")
    private List<Restoran> restorani;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Restoran> getRestorani() {
        return restorani;
    }

    public void setRestorani(List<Restoran> restorani) {
        this.restorani = restorani;
    }
}
