package com.rgarcia.controlvisitas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "serie")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String num_serie;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_instalacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_vencimiento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fin_contrato;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnoreProperties("series")
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
