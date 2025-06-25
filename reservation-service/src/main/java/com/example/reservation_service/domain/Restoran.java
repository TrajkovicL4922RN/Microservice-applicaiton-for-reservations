package com.example.reservation_service.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restoran")
public class Restoran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ime;

    @Column(nullable = false)
    private String adresa;

    private String opis;

    @Column(name = "radno_vreme")
    private String radnoVreme;

    @ManyToMany
    @JoinTable(
            name = "restoran_kuhinja",
            joinColumns = @JoinColumn(name = "restoran_id"),
            inverseJoinColumns = @JoinColumn(name = "kuhinja_id")
    )
    private List<Kuhinja> kuhinje;

    @OneToMany(mappedBy = "restoran")
    private List<Pogodnost> pogodnosti;

    @OneToMany(mappedBy = "restoran")
    private List<Sto> stolovi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public List<Kuhinja> getKuhinje() {
        return kuhinje;
    }

    public void setKuhinje(List<Kuhinja> kuhinje) {
        this.kuhinje = kuhinje;
    }

    public List<Pogodnost> getPogodnosti() {
        return pogodnosti;
    }

    public void setPogodnosti(List<Pogodnost> pogodnosti) {
        this.pogodnosti = pogodnosti;
    }

    public List<Sto> getStolovi() {
        return stolovi;
    }

    public void setStolovi(List<Sto> stolovi) {
        this.stolovi = stolovi;
    }
}
