package com.rgarcia.controlvisitas.service;

import com.rgarcia.controlvisitas.entity.Cliente;
import com.rgarcia.controlvisitas.entity.Producto;
import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.repository.ClienteRepository;
import com.rgarcia.controlvisitas.repository.ProductoRepository;
import com.rgarcia.controlvisitas.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private ClienteRepository clienteRepo;

    public Serie createSerie(Long productoId, Serie serie) {
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(()->new RuntimeException("Producto no encontrado con id "+ productoId));
        serie.setProducto(producto);
        return serieRepo.save(serie);
    }


    public Serie getSerie(Long id) {
        Serie serie = serieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Serie no encontrada con id " + id));
        return serie;
    }

    public List<Serie> todasSerie() {
        return serieRepo.findAll();
    }


    public void deleteSerie(Long serieId){
        Serie serie = serieRepo.findById(serieId)
                .orElseThrow(()-> new RuntimeException("Serie no encontrado con id "+serieId));
        serieRepo.delete(serie);
    }

    public Serie updateSerie(Long serieId, Serie nvaSerie) {
        return serieRepo.findById(serieId)
                .map(serie -> {
                    if (serie.getNum_serie() != null) serie.setNum_serie(nvaSerie.getNum_serie());
                    if (serie.getFecha_instalacion()!=null)serie.setFecha_instalacion(nvaSerie.getFecha_instalacion());
                    if(serie.getFecha_vencimiento()!=null)serie.setFecha_vencimiento(nvaSerie.getFecha_vencimiento());
                    if(serie.getFin_contrato()!=null)serie.setFin_contrato(nvaSerie.getFin_contrato());
                    return serieRepo.save(serie);
                })
                .orElseThrow(()-> new RuntimeException("Serie no encontrada con id" + serieId));
    }

    public void borrarSerieProduto(Long id, Long serieId) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        Serie serie = producto.getSerie().stream()
                .filter(s -> s.getId().equals(serieId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Serie no encontrada con id " + serieId));
       producto.getSerie().remove(serie);
        productoRepo.save(producto);
    }
    public Serie serieACliente(Long clienteId, Long serieId) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(()->new RuntimeException("Cliente no encontrado con id "+clienteId));
        Serie serie = serieRepo.findById(serieId)
                        .orElseThrow(()->new RuntimeException("Serie no encontrar con id "+serieId));
        serie.setCliente(cliente);
        return serieRepo.save(serie);
    }
}
