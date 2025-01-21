package com.mycompany.tpdsw.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ItemMenuDto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
    private Integer idVendedor;

}
