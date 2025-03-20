package com.mycompany.tpdsw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PedidoItemPedidoDto {

    private Integer id;
    private Integer pedidoDtoId;
    private ItemPedidoDto itemPedidoDto;
}
