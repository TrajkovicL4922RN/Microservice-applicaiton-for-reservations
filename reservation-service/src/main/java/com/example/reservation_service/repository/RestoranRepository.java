package com.example.reservation_service.repository;

import com.example.reservation_service.domain.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestoranRepository extends JpaRepository<Restoran, Long> {
    Restoran findRestoranById(long id);
}
