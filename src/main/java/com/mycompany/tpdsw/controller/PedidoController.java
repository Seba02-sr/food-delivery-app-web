package com.mycompany.tpdsw.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;
import com.mycompany.tpdsw.service.PedidoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/save")
    public ResponseEntity<?> savePedido(@RequestBody PedidoDto pedidoDto) throws URISyntaxException {
        if (pedidoDto.getId() != null && pedidoDto.getClienteId() != null) {
            return ResponseEntity.badRequest().build();
        }
        PedidoDto newPedidoDto = pedidoService.save(pedidoDto);
        return ResponseEntity.created(new URI("/api/pedido" + newPedidoDto.getId())).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto)
            throws PedidoNoEncontradoException, PagoNoEncontradoException {

        Optional<PedidoDto> pedidoDtoOpt = Optional.ofNullable(pedidoService.findById(id));
        if (pedidoDtoOpt.isPresent()) {

            pedidoService.update(pedidoDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
