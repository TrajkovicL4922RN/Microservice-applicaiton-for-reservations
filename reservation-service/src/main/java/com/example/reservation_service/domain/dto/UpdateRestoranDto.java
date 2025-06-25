package com.example.reservation_service.domain.dto;

import com.example.reservation_service.domain.Kuhinja;
import com.example.reservation_service.domain.Pogodnost;
import com.example.reservation_service.domain.Sto;
import jakarta.persistence.*;

import java.util.List;

public class UpdateRestoranDto {
    private String ime;
    private String adresa;
    private String opis;
    private String radnoVreme;
    private List<Kuhinja> kuhinje;


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
}
