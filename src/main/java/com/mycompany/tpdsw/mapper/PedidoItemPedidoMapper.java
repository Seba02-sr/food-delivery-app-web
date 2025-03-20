package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.PedidoItemPedidoDto;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.model.relacion.PedidoItemPedido;

import lombok.Builder;

@Builder
@Component
public class PedidoItemPedidoMapper implements Mapper<PedidoItemPedido, PedidoItemPedidoDto> {

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Override
    public PedidoItemPedidoDto mapToDto(PedidoItemPedido entity) {
        return PedidoItemPedidoDto.builder()
                .id(entity.getId())
                .pedidoDtoId(entity.getPedido().getId())
                .itemPedidoDto(itemPedidoMapper.mapToDto(entity.getItemPedido()))
                .build();
    }

    @Override
    public PedidoItemPedido mapToEntity(PedidoItemPedidoDto dto) {
        Pedido pedidoProxy = Pedido.builder()
                .id(dto.getPedidoDtoId())
                .build();
        return PedidoItemPedido.builder()
                .id(dto.getId())
                .pedido(pedidoProxy)
                .itemPedido(itemPedidoMapper.mapToEntity(dto.getItemPedidoDto()))
                .build();
    }

}
