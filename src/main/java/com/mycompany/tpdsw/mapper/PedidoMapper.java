package com.mycompany.tpdsw.mapper;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.controller.VendedorController;
import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.dto.PagoDto;
import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.model.Pago;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.service.ClienteService;
import com.mycompany.tpdsw.service.PagoService;

import lombok.Builder;

@Builder
@Component
public class PedidoMapper implements Mapper<Pedido, PedidoDto> {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PagoService pagoService;

    @Autowired
    @Lazy
    private PedidoItemPedidoMapper pedidoItemPedidoMapper;

    @Override
    public PedidoDto mapToDto(Pedido entity) {
        Pago formaDePago = entity.getFormaPago();
        Integer idFormaDePago = null;
        if (formaDePago != null) {
            idFormaDePago = formaDePago.getId();
        }
        return PedidoDto.builder()
                .clienteId(entity.getCliente().getId())
                .id(entity.getId())
                .estado(entity.getEstado())
                .formaPagoDtoId(idFormaDePago)
                .build();
    }

    @Override
    public Pedido mapToEntity(PedidoDto dto) { // Falta la lista de pedidos
        ClienteDto clienteDto;
        Cliente cliente = null;
        PagoDto pagoDto;
        Pago pago = null;
        try {
            clienteDto = clienteService.findByIdAndActive(dto.getClienteId());
            cliente = clienteMapper.mapToEntity(clienteDto);
            logger.info("forma de pago {}", dto.getFormaPagoDtoId());
            if (dto.getFormaPagoDtoId() != null) {
                pagoDto = pagoService.findById(dto.getFormaPagoDtoId());
                logger.info("Pago del dto {}", pagoDto);
                pago = pagoMapper.mapToEntity(pagoDto);
            }

        } catch (ClienteNoEncontradoException | PagoNoEncontradoException e) {
            return null;
        }

        return Pedido.builder()
                .id(dto.getId())
                .estado(dto.getEstado())
                .cliente(cliente)
                .pedidoItemPedidos(dto.getPedidoItemPedidosDto().stream()
                        .map(pedidoItemPedidoMapper::mapToEntity)
                        .collect(Collectors.toList()))
                .formaPago(pago)
                .build();

    }

    public void updateEntityFromDto(PedidoDto pedidoDto, Pedido existingPedido) throws PagoNoEncontradoException {
        if (pedidoDto.getEstado() != null) {
            existingPedido.setEstado(pedidoDto.getEstado());
        }
        if (pedidoDto.getPedidoItemPedidosDto() != null) {
            existingPedido.getPedidoItemPedidos()
                    .addAll(pedidoDto.getPedidoItemPedidosDto().stream().map(pedidoItemPedidoMapper::mapToEntity)
                            .collect(Collectors.toList()));
        }
        if (pedidoDto.getFormaPagoDtoId() != null) {
            PagoDto pagoDto = pagoService.findById(pedidoDto.getFormaPagoDtoId());
            existingPedido.setFormaPago(pagoMapper.mapToEntity(pagoDto));
        }

    }

}
