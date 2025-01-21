package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;

public interface ClienteService {

    List<ClienteDto> findAllActive();

    ClienteDto findByIdAndActive(Integer id) throws ClienteNoEncontradoException;

    ClienteDto save(ClienteDto clienteDto);

    void delete(ClienteDto clienteDto) throws ClienteNoEncontradoException;

    ClienteDto update(ClienteDto clienteDto) throws ClienteNoEncontradoException;

}
