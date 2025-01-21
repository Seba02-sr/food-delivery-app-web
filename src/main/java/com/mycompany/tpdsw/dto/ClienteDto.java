package com.mycompany.tpdsw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String cuit;
    private String direccion;
    private String email;
    private CoordenadaDto coordenadaDto;

}
