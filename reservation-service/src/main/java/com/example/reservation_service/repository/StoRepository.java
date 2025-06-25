package com.example.reservation_service.repository;

import com.example.reservation_service.domain.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StoRepository extends JpaRepository<Sto, Long> {
    @Query("SELECT s FROM Sto s WHERE s.brojMesta >= :brojMesta AND " +
            "NOT EXISTS (SELECT r FROM Rezervacija r WHERE r.sto.id = s.id AND r.datum = :datum AND r.vremeTrajanja = :vreme)")
    List<Sto> findSlobodniStolovi(@Param("datum") LocalDate datum,
                                  @Param("vreme") Integer vreme,
                                  @Param("brojMesta") Integer brojMesta);


}
