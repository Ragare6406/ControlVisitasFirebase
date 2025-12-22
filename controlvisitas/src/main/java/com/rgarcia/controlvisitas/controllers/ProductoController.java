package com.rgarcia.controlvisitas.controllers;

import com.rgarcia.controlvisitas.entity.Producto;
import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.service.ProductoService;
import com.rgarcia.controlvisitas.service.SerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SerieService serieService;

    /**
     * Crear un producto nuevo
     */
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.createProducto(producto));
    }

    /**
     * Obtener un producto
     */
    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {
        return productoService.getProduto(id);
    }

    /**
     * Obtener todos los productos
     */
    @GetMapping
    public List<Producto> listaProducto() {
        return productoService.todosProductos();
    }

    /**
     * Borrar producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long productoId) {
        try {
            productoService.deleteProducto(productoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Modificar un producto
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            return ResponseEntity.ok(productoService.updateProducto(id, producto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crear una serie en un producto
     */
    @PostMapping("/{id}/serie")
    public ResponseEntity<Serie> agregarSerie(
            @PathVariable Long id,
            @RequestBody Serie serie) {
        return ResponseEntity.ok(serieService.createSerie(id, serie));
    }

    /**
     * Borrar una serie de un producto
     */
    @DeleteMapping("/{id}/serie/{serieId}")
    public ResponseEntity<Void> borrarVisitaACliente(@PathVariable Long id, @PathVariable Long visitaId) {
        try {
            serieService.borrarSerieProduto(id, visitaId);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


}
