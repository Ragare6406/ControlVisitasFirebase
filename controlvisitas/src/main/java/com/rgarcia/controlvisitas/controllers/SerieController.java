package com.rgarcia.controlvisitas.controllers;

import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieService;


    /**
     * Obtener un serie
     */
    @GetMapping("/{id}")
    public Serie getSerie(@PathVariable Long id) {
        return serieService.getSerie(id);
    }

    /**
     * Obtener todas las series
     */
    @GetMapping
    public List<Serie> listaSeries() {
        return serieService.todasSerie();
    }


    /**
     * Borrar serie
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarserie(@PathVariable Long serieId) {
        try {
            serieService.deleteSerie(serieId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Modificar un serie
     */
    @PutMapping("/{id}")
    public ResponseEntity<Serie> modificarSerie(@PathVariable Long id, @RequestBody Serie serie) {
        try {
            return ResponseEntity.ok(serieService.updateSerie(id, serie));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
