package com.rgarcia.controlvisitas.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClienteVisitasDTO {
    private String empresa;
    private String direccion;
    private String municipio;
    private String contacto;
    private String telefono;
    private LocalDate fecha;
    private LocalTime hora_llegada;
    private LocalTime hora_salida;
    private String observaciones;

}
