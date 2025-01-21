package com.mycompany.tpdsw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CoordenadaDto {

    private Integer id;
    private Double latitud;
    private Double longitud;

}
