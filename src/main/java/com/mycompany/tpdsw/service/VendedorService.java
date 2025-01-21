package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.exception.VendedorNoEncontradoException;

public interface VendedorService {

    List<VendedorDto> findAllByActivoTrue();

    VendedorDto findById(Integer id) throws VendedorNoEncontradoException;

    List<VendedorDto> findByNombre(String nombre) throws VendedorNoEncontradoException;

    VendedorDto save(VendedorDto vendedorDto);

    void delete(VendedorDto vendedorDto) throws VendedorNoEncontradoException;

    VendedorDto update(VendedorDto vendedorDto) throws VendedorNoEncontradoException;

}
