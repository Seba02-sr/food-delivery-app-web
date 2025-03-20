package com.mycompany.tpdsw.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.dto.PagoDto;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;
import com.mycompany.tpdsw.mapper.PagoMapper;
import com.mycompany.tpdsw.model.Pago;
import com.mycompany.tpdsw.repository.PagoRepository;
import com.mycompany.tpdsw.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Override
    public PagoDto findById(Integer id) throws PagoNoEncontradoException {
        Optional<Pago> pagoOpt = pagoRepository.findById(id);

        if (!pagoOpt.isPresent()) {
            throw new PagoNoEncontradoException("No se ha encontrado Pago con id: " + id);
        }

        Pago pago = pagoOpt.get();

        return pagoMapper.mapToDto(pago);

    }

}
