package com.mycompany.tpdsw.service;

import com.mycompany.tpdsw.dto.PagoDto;
import com.mycompany.tpdsw.exception.PagoNoEncontradoException;

public interface PagoService {

    PagoDto findById(Integer id) throws PagoNoEncontradoException;
}
