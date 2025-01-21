package com.mycompany.tpdsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class BebidaDto extends ItemMenuDto {
    private Double graduacionAlcoholica;
    private Double tamano;
    private Double volumen;

}
