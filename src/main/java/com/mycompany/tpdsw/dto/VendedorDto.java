package com.mycompany.tpdsw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class VendedorDto {

    private Integer id;
    private String nombre;
    private String direccion;
    private CoordenadaDto coordenadaDto;

}