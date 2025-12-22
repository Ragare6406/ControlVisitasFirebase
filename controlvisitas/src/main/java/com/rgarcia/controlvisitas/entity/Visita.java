package com.rgarcia.controlvisitas.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visita")

public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)   //no se puede actualizar el dato
@JsonFormat(pattern = "yyyy-MM-dd")
@JsonProperty("fecha")
private LocalDate fecha;

@JsonFormat(pattern = "HH:mm")
private LocalTime hora_llegada;
@JsonFormat(pattern = "HH:mm")
private LocalTime hora_salida;
@Column(length = 2000)
private String observaciones;


@ManyToOne
@JoinColumn(name = "cliente_id")
private Cliente cliente;
}
