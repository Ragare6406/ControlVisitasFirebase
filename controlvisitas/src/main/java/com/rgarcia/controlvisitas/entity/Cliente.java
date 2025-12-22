package com.rgarcia.controlvisitas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String empresa;
    @NotBlank
    private String direccion;
    @NotBlank
    private String municipio;
    @Column(unique = true)
    @Email
    private String email;
    private String contacto;
    private String telefono;

    //Relación con "producto"
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Serie> serie = new ArrayList<>();

    //Relacion con "visita"
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visita> visita = new ArrayList<>();



//    public Long getId() {return id;}
//    public void setId(Long id) {this.id = id;}
//    public @NotBlank String getEmpresa() {return empresa;}
//    public void setEmpresa(@NotBlank String empresa) {this.empresa = empresa;}
//    public @NotBlank String getDireccion() {return direccion;}
//    public void setDireccion(@NotBlank String direccion) {this.direccion = direccion;}
//    public @NotBlank String getMunicipio() {return municipio;}
//    public void setMunicipio(@NotBlank String municipio) {this.municipio = municipio;}
//    public @Email String getEmail() {return email;}
//    public void setEmail(@Email String email) {this.email = email;}
//    public String getContacto() {return contacto;}
//    public void setContacto(String contacto) {this.contacto = contacto;}
//    public String getTelefono() {return telefono;}
//    public void setTelefono(String telefono) {this.telefono = telefono;}
//    public List<ProductoSerie> getProductoSerie() {return productoSerie;}
//    public void setProductoSerie(List<ProductoSerie> productoSerie) {this.productoSerie = productoSerie;}
//    public List<Visita> getVisita() {return visita;}
//
//    public void setVisita(List<Visita> visita) {this.visita = visita;}

}
