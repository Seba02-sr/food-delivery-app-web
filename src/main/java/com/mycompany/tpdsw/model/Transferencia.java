package com.mycompany.tpdsw.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
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
public class Transferencia extends Pago {

    private String cbu;

    private String cuit;

    /**
     * Calcula el monto total a pagar aplicando un recargo del 2%.
     * 
     * @param monto El monto base sobre el cual se aplicará el recargo.
     * @return El monto total a pagar con el recargo incluido.
     */
    public BigDecimal pagar(BigDecimal monto) {
        return monto.multiply(new BigDecimal(1.02));
    }

}
