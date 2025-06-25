package com.example.reservation_service.repository;

import com.example.reservation_service.domain.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    List<Rezervacija> findByStoRestoranIdAndDostupnoTrueAndDatum(
            Long restoranId, LocalDate datum);

    List<Rezervacija> findByStoBrojMestaGreaterThanEqualAndDostupnoTrueAndDatumAndVremeTrajanja(
            Integer brojMesta, LocalDate datum, Integer vremeTrajanja);

    @Query("select r from Rezervacija r where r.dostupno=true")
    List<Rezervacija> findByDostupno();


}
