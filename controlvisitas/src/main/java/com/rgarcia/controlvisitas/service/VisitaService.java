package com.rgarcia.controlvisitas.service;

import com.rgarcia.controlvisitas.entity.Cliente;
import com.rgarcia.controlvisitas.entity.Visita;
import com.rgarcia.controlvisitas.repository.ClienteRepository;
import com.rgarcia.controlvisitas.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitaService {

    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private VisitaRepository visitaRepo;

    public Visita crearVisita(Long clienteId, Visita visita) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        visita.setCliente(cliente);
        return visitaRepo.save(visita);
    }

    public List<Visita> buscarVisitas(LocalDate fecha) {
        return visitaRepo.findByFecha(fecha);
    }

    public void borrarVisita(Long visitaId) {
        Visita visita = visitaRepo.findById(visitaId)
                .orElseThrow(() -> new RuntimeException("Visita no encontrado con id " + visitaId));
        visitaRepo.delete(visita);
    }

    public void borrarVisitaCliente(Long id, Long visitaId) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
        Visita visita = cliente.getVisita().stream()
                .filter(v -> v.getId().equals(visitaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id " + visitaId));
        cliente.getVisita().remove(visita);
        clienteRepo.save(cliente);
    }

    public void borrarVisitaClientePorFecha(Long id, LocalDate fecha) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
        Visita visita = cliente.getVisita().stream()
                .filter(v -> v.getFecha().equals(fecha))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con fecha " + fecha));
        cliente.getVisita().remove(visita);
        clienteRepo.save(cliente);
    }

    public Visita updateVisita(Long id, Visita nvaVisita) {
        return visitaRepo.findById(id)
                .map(visita -> {
//                    if (visita.getFecha() != null) visita.setFecha(nvaVisita.getFecha());
//                    if (visita.getHora_llegada()!=null) visita.setHora_llegada(nvaVisita.getHora_llegada());
//                    if (visita.getHora_salida()!=null) visita.setHora_salida(nvaVisita.getHora_salida());
//                    if (visita.getObservaciones()!=null) visita.setObservaciones(nvaVisita.getObservaciones());
                    if (nvaVisita.getFecha() != null) visita.setFecha(nvaVisita.getFecha());
                    if (nvaVisita.getHora_llegada() != null) visita.setHora_llegada(nvaVisita.getHora_llegada());
                    if (nvaVisita.getHora_salida() != null) visita.setHora_salida(nvaVisita.getHora_salida());
                    if (nvaVisita.getObservaciones() != null) visita.setObservaciones(nvaVisita.getObservaciones());
                    return visitaRepo.save(visita);
                })
                .orElseThrow(()-> new RuntimeException("Visita no encontrado con id" + id));
    }


}
