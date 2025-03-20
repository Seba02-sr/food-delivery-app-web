package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.ItemMenuDto;
import com.mycompany.tpdsw.dto.ItemPedidoDto;
import com.mycompany.tpdsw.model.ItemPedido;
import com.mycompany.tpdsw.service.ItemMenuService;

import lombok.Builder;

@Builder
@Component
public class ItemPedidoMapper implements Mapper<ItemPedido, ItemPedidoDto> {

    @Autowired
    private ItemMenuService itemMenuService;

    private ItemMenuMapper itemMenuMapper;

    @Override
    public ItemPedidoDto mapToDto(ItemPedido entity) {
        return ItemPedidoDto.builder()
                .id(entity.getId())
                .cantidad(entity.getCantidad())
                .itemMenuDtoId(entity.getItemMenu().getId())
                .build();
    }

    @Override
    public ItemPedido mapToEntity(ItemPedidoDto dto) {
        ItemMenuDto itemMenuDto = itemMenuService.findActiveById(dto.getItemMenuDtoId());
        return ItemPedido.builder()
                .id(dto.getId())
                .cantidad(dto.getCantidad())
                .itemMenu(itemMenuMapper.mapToEntity(itemMenuDto))
                .build();
    }
}
