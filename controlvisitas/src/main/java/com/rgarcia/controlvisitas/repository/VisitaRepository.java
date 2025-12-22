package com.rgarcia.controlvisitas.repository;

import com.rgarcia.controlvisitas.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findByFecha(LocalDate fecha);
}
