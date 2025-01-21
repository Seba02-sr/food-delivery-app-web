package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.CategoriaDto;

public interface CategoriaService {

    CategoriaDto findByNombre(String nombre);

    List<String> findNameOfAllCategoria();
}
