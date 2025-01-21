package com.mycompany.tpdsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.service.CategoriaService;

@Component
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    public List<String> findNameOfAllCategoria() {
        return categoriaService.findNameOfAllCategoria();
    }
}
