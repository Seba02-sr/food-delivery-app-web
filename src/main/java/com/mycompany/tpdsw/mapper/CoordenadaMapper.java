package com.mycompany.tpdsw.mapper;

import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.CoordenadaDto;
import com.mycompany.tpdsw.model.Coordenada;

import lombok.Builder;

@Builder

@Component
public class CoordenadaMapper implements Mapper<Coordenada, CoordenadaDto> {

    @Override
    public CoordenadaDto mapToDto(Coordenada coordenada) {
        return CoordenadaDto.builder()
                .id(coordenada.getId())
                .latitud(coordenada.getLatitud())
                .longitud(coordenada.getLongitud())
                .build();
    }

    @Override
    public Coordenada mapToEntity(CoordenadaDto coordenadaDto) {
        return Coordenada.builder()
                .id(coordenadaDto.getId())
                .latitud(coordenadaDto.getLatitud())
                .longitud(coordenadaDto.getLongitud())
                .build();
    }

}
