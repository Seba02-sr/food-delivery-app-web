package com.mycompany.tpdsw.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class PagoDto {

    private Integer id;
    private LocalDate fechaPago;
    private BigDecimal monto;
}
