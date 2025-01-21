package com.mycompany.tpdsw.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.dto.CategoriaDto;
import com.mycompany.tpdsw.mapper.CategoriaMapper;
import com.mycompany.tpdsw.model.Categoria;
import com.mycompany.tpdsw.repository.CategoriaRepository;
import com.mycompany.tpdsw.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDto findByNombre(String nombre) {
        Categoria categoria = categoriaRepository.findByNombre(nombre);
        return categoriaMapper.mapToDto(categoria);
    }

    @Override
    public List<String> findNameOfAllCategoria() {
        return categoriaRepository.findAll().stream().map(Categoria::getNombre).collect(Collectors.toList());
    }

}
