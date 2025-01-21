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

public class PlatoDto extends ItemMenuDto {

    private Double calorias;
    private Boolean aptoCeliaco;
    private Boolean aptoVegetariano;
    private Boolean aptoVegano;
    private Double peso;

}
