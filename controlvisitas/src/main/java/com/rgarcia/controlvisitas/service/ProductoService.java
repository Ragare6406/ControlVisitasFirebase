package com.rgarcia.controlvisitas.service;

import com.rgarcia.controlvisitas.entity.Producto;
import com.rgarcia.controlvisitas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepo;

    public Producto createProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    public Producto getProduto(Long id) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        return producto;
    }

    public List<Producto> todosProductos() {
        return productoRepo.findAll();
    }

    public Producto productoPorEquipo(String equipo){
        return productoRepo.findByEquipo(equipo);
    }

    public void deleteProducto(Long productoId){
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado con id "+productoId));
        productoRepo.delete(producto);
    }

    public Producto updateProducto(Long productoId, Producto nvoProducto) {
        return productoRepo.findById(productoId)
                .map(producto -> {
                    if (producto.getEquipo() != null) producto.setEquipo(nvoProducto.getEquipo());
                    return productoRepo.save(producto);
                })
                .orElseThrow(()-> new RuntimeException("Producto no encontrado con id" + productoId));
    }

}
