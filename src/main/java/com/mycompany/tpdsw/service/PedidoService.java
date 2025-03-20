package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;

public interface PedidoService {

    List<PedidoDto> findAllActive();

    PedidoDto save(PedidoDto pedidoDto);

    PedidoDto update(PedidoDto pedidoDto) throws PedidoNoEncontradoException, PagoNoEncontradoException;

    void delete(PedidoDto pedidoDto) throws PedidoNoEncontradoException;

    PedidoDto findById(Integer id) throws PedidoNoEncontradoException;

}
