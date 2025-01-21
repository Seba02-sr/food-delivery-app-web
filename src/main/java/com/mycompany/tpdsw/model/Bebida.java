package com.mycompany.tpdsw.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "bebida")
public class Bebida extends ItemMenu {

    @Column(name = "graduacion_alcoholica")
    @Min(0)
    @Max(100)
    private Double graduacionAlcoholica;

    @Column(nullable = false)
    @Min(0)
    private Double tamano;

    @Column(nullable = false)
    @Min(0)
    private Double volumen;

    /**
     * Calcula el peso de la bebida considerando su volumen, tipo y 20% por envases.
     * - Bebida con alcohol: 0.99
     * - Bebida gaseosa: 1.04
     * 
     * Formula -> Volumen * <Factor Tipo> * 1.2
     */
    @Override
    public Double peso() {
        if (this.graduacionAlcoholica > 0) {
            return this.volumen * 0.99 * 1.2;
        } else {
            return this.volumen * 1.04 * 1.2;
        }
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
        return false;
    }

}
