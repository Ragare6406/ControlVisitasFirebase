package com.rgarcia.controlvisitas.controllers;


import com.rgarcia.controlvisitas.entity.Visita;
import com.rgarcia.controlvisitas.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/visita")

public class VisitaController {
    @Autowired
    private VisitaService visitaService;

    /**
     * Obtener las visitas por fecha
     */
    @GetMapping("/{fecha}")
    public List<Visita> listaVisitas(@PathVariable LocalDate fecha) {
        return visitaService.buscarVisitas(fecha);
    }

    /**
     * Modificar una visite
     */
    @PutMapping("/{id}")
    public ResponseEntity<Visita> modificarVisita(@PathVariable Long id, @RequestBody Visita visita) {
        try {
            return ResponseEntity.ok(visitaService.updateVisita(id, visita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

}



