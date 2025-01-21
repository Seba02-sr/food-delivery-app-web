package com.mycompany.tpdsw.dto;

import com.mycompany.tpdsw.model.TipoCategoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class CategoriaDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private TipoCategoria tipo; // Comida, bebida
}
