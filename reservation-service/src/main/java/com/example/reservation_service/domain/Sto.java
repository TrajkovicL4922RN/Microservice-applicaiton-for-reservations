package com.example.reservation_service.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sto")
public class Sto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "broj_mesta")
    private Integer brojMesta;

    @Column(name = "zona_pusenja")
    private Boolean zonaPusenja;

    @Column(name = "zona_mesto")
    private Boolean zonaMesto;

    @ManyToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(Integer brojMesta) {
        this.brojMesta = brojMesta;
    }

    public Boolean getZonaPusenja() {
        return zonaPusenja;
    }

    public void setZonaPusenja(Boolean zonaPusenja) {
        this.zonaPusenja = zonaPusenja;
    }

    public Boolean getZonaMesto() {
        return zonaMesto;
    }

    public void setZonaMesto(Boolean zonaMesto) {
        this.zonaMesto = zonaMesto;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
