package com.rgarcia.controlvisitas.controllers;

import com.rgarcia.controlvisitas.entity.Cliente;
import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.entity.Visita;
import com.rgarcia.controlvisitas.service.ClienteService;
import com.rgarcia.controlvisitas.service.SerieService;
import com.rgarcia.controlvisitas.service.VisitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VisitaService visitaService;
    @Autowired
    private SerieService serieService;

    /**
     * Crear un cliente nuevo
     */
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.createClient(cliente));
    }

    /**
     * Obtener un cliente
     */
    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        return clienteService.getCliente(id);
    }

    /**
     * Obtener todos los clientes
     */
    @GetMapping
    public List<Cliente> listaClientes() {
        return clienteService.todosClientes();
    }

    /**
     * Borrar cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Long id) {
        try {
            clienteService.borrarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/serie")
    public ResponseEntity<List<Serie>>getSerieByCliente(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.getSerieByCliente(id));
    }



    /**
     * Crear una visita en un cliente
     */
    @PostMapping("/{id}/visita")
    public ResponseEntity<Visita> agregarVisita(
            @PathVariable Long id,
            @RequestBody Visita visita) {
        return ResponseEntity.ok(visitaService.crearVisita(id, visita));
    }

    /**
     * Borrar una visita de un cliente
     */
    @DeleteMapping("/{id}/visita/{visitaId}")
    public ResponseEntity<Void> borrarVisitaACliente(@PathVariable Long id, @PathVariable Long visitaId) {
        try {
            visitaService.borrarVisitaCliente(id, visitaId);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Borrar una visita de un cliente por fecha
     */
    @DeleteMapping("/{id}/visita/{fecha}")
    public ResponseEntity<Void> borrarVisitaAClientePorFecha(@PathVariable Long id, @PathVariable LocalDate fecha) {
        try {
            visitaService.borrarVisitaClientePorFecha(id, fecha);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Modificar un cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.updateCliente(id, cliente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Crear una serie en un cliente
     */
    @PostMapping("/{clienteId}/serie/{serieId}")
    public ResponseEntity<Serie> agregarSerie(
            @PathVariable Long clienteId,
            @PathVariable Long serieId) {
        return ResponseEntity.ok(serieService.serieACliente(clienteId, serieId));
    }


}

