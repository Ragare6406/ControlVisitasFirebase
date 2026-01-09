package com.rgarcia.controlvisitas.service;

import com.rgarcia.controlvisitas.entity.Cliente;
import com.rgarcia.controlvisitas.entity.Producto;
import com.rgarcia.controlvisitas.entity.Serie;
import com.rgarcia.controlvisitas.repository.ClienteRepository;
import com.rgarcia.controlvisitas.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private VisitaRepository visitaRepo;

    public Cliente createClient(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public Cliente getCliente(Long clientId) {
        Cliente cliente = clienteRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clientId));
        return cliente;
    }

    public Page<Cliente> todosClientes(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        return clienteRepo.findAll(PageRequest.of(page,size));
    }

    public void borrarCliente(Long clienteId) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        clienteRepo.delete(cliente);
    }

    public Cliente updateCliente(Long clienteId, Cliente nvoCliente) {
        return clienteRepo.findById(clienteId)
                .map(cliente -> {
                    if (cliente.getEmpresa() != null) cliente.setEmpresa(nvoCliente.getEmpresa());
                    if (cliente.getDireccion()!=null) cliente.setDireccion(nvoCliente.getDireccion());
                    if (cliente.getMunicipio()!=null) cliente.setMunicipio(nvoCliente.getMunicipio());
                    if (cliente.getContacto()!=null) cliente.setContacto(nvoCliente.getContacto());
                    if (cliente.getEmail()!=null) cliente.setEmail(nvoCliente.getEmail());
                    if (cliente.getTelefono()!=null) cliente.setTelefono(nvoCliente.getTelefono());
                    return clienteRepo.save(cliente);
                })
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado con id" + clienteId));
    }

    public List<Serie> getSerieByCliente(Long idCliente){
        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        return cliente.getSerie();
    }


    public List<Producto> getProductoByCliente(Long idCliente){
        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        return cliente.getProductos();
    }

}

