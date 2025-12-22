package com.rgarcia.controlvisitas.repository;

import com.rgarcia.controlvisitas.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByEquipo (String equipo);
}
