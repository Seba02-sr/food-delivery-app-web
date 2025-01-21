package com.mycompany.tpdsw.dto;

import com.mycompany.tpdsw.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {

    private Integer clienteId;
    private Integer id;
    private Estado estado;
    private PagoDto formaPagoDto;

}
