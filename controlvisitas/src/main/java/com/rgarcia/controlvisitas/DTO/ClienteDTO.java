package com.rgarcia.controlvisitas.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor

public record ClienteDTO(
        Long id,
        String empresa,
        String direccion,
        String municipio,
        String email,
        String contacto,
        String telefono
) {}
