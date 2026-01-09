package com.rgarcia.controlvisitas.service;

import com.rgarcia.controlvisitas.entity.Cliente;
import com.rgarcia.controlvisitas.entity.Producto;
import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.repository.ClienteRepository;
import com.rgarcia.controlvisitas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private ClienteRepository clienteRepo;

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

    public Producto prodductoToCliente(Long clienteId, Long productoId) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(()->new RuntimeException("Cliente no encontrado con id "+clienteId));
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(()->new RuntimeException("Serie no encontrar con id "+productoId));
        cliente.getProductos().add(producto);
        producto.getClientes().add(cliente);
        clienteRepo.save(cliente);
        return productoRepo.save(producto);
    }

}
