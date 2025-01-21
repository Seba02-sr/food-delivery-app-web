package com.mycompany.tpdsw.mapper;

import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.CategoriaDto;
import com.mycompany.tpdsw.model.Categoria;

import lombok.Builder;

@Builder
@Component
public class CategoriaMapper implements Mapper<Categoria, CategoriaDto> {

    @Override
    public CategoriaDto mapToDto(Categoria entity) {
        return CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .tipo(entity.getTipo())
                .build();

    }

    @Override
    public Categoria mapToEntity(CategoriaDto dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .tipo(dto.getTipo())
                .build();
    }

}
