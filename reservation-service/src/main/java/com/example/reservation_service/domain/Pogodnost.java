package com.example.reservation_service.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pogodnost")
public class Pogodnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv_pogodnosti")
    private String nazivPogodnosti;

    private String opis;

    private Integer brojRezervacija;

    @ManyToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

}
