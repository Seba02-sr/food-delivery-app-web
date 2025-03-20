package com.mycompany.tpdsw.impl;

import com.mycompany.tpdsw.service.PedidoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.tpdsw.controller.VendedorController;
import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.dto.PedidoItemPedidoDto;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;
import com.mycompany.tpdsw.mapper.PedidoMapper;
import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.model.ItemPedido;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.model.relacion.PedidoItemPedido;
import com.mycompany.tpdsw.repository.ItemMenuRepository;
import com.mycompany.tpdsw.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ItemMenuRepository itemMenuRepository;

    @Override
    public List<PedidoDto> findAllActive() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedidoMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Registra un nuevo pedido en el sistema.
     * Convierte el DTO recibido en objeto dominio y se persiste
     * 
     * @param pedidoDto El objeto DTO que contiene los datos del pedido a registrar.
     */
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        Pedido pedido = pedidoMapper.mapToEntity(pedidoDto);

        Pedido newPedido = pedidoRepository.save(pedido);
        logger.info("Pedido guardado: {}", newPedido);
        return pedidoMapper.mapToDto(newPedido);
    }

    @Override
    @Transactional
    public PedidoDto update(PedidoDto pedidoDto) throws PedidoNoEncontradoException, PagoNoEncontradoException {
        Integer id = pedidoDto.getId();
        // Recupera el pedido existente
        Pedido existingPedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException("Pedido " + id + " no encontrado"));

        // Actualiza los campos básicos del pedido
        pedidoMapper.updateEntityFromDto(pedidoDto, existingPedido);

        // Reconstruir la lista de asociaciones (PedidoItemPedido)
        List<PedidoItemPedido> newPedidoItemPedidos = new ArrayList<>();

        if (pedidoDto.getPedidoItemPedidosDto() != null) {
            for (PedidoItemPedidoDto pipDto : pedidoDto.getPedidoItemPedidosDto()) {
                // Crea la asociación
                PedidoItemPedido pip = new PedidoItemPedido();
                pip.setPedido(existingPedido); // Asocia el pedido

                // Crea el ItemPedido asociado
                ItemPedido itemPedido = ItemPedido.builder().build();
                itemPedido.setCantidad(pipDto.getItemPedidoDto().getCantidad());

                // Busca y asigna el ItemMenu correspondiente
                ItemMenu itemMenu = itemMenuRepository.findById(
                        pipDto.getItemPedidoDto().getItemMenuDtoId())
                        .orElseThrow(() -> new RuntimeException("ItemMenu no encontrado"));
                itemPedido.setItemMenu(itemMenu);

                // Establece la relación en ambos lados:
                pip.setItemPedido(itemPedido);
                itemPedido.setPedidoItemPedido(pip);

                newPedidoItemPedidos.add(pip);
            }
        }

        existingPedido.setPedidoItemPedidos(newPedidoItemPedidos);

        Pedido savedPedido = pedidoRepository.save(existingPedido);

        return pedidoMapper.mapToDto(savedPedido);
    }

    @Override
    public void delete(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoMapper.mapToEntity(pedidoDto);
        Integer id = pedido.getId();
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNoEncontradoException("Pedido " + id + " no encontrado");
        }
        pedidoRepository.delete(pedido);

    }

    @Override
    public PedidoDto findById(Integer id) throws PedidoNoEncontradoException {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (!pedidoOpt.isPresent()) {
            throw new PedidoNoEncontradoException("No se ha encontrado pedido con id: " + id);
        }

        Pedido pedido = pedidoOpt.get();

        return pedidoMapper.mapToDto(pedido);

    }

}
