package com.example.reservation_service.repository;

import com.example.reservation_service.domain.Kuhinja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KuhinjaRepository extends JpaRepository<Kuhinja, Long> {
}
