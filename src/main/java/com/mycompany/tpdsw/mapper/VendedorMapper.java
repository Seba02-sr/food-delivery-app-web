package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.model.Vendedor;

import lombok.Builder;

@Builder
@Component
public class VendedorMapper implements Mapper<Vendedor, VendedorDto> {

    @Autowired
    private final CoordenadaMapper coordenadaMapper;

    @Override
    public VendedorDto mapToDto(Vendedor vendedor) {
        return VendedorDto.builder()
                .id(vendedor.getId())
                .nombre(vendedor.getNombre())
                .direccion(vendedor.getDireccion())
                .coordenadaDto(coordenadaMapper.mapToDto(vendedor.getCoordenada()))
                .build();
    }

    @Override
    public Vendedor mapToEntity(VendedorDto vendedorDto) {
        return Vendedor.builder()
                .id(vendedorDto.getId())
                .nombre(vendedorDto.getNombre())
                .direccion(vendedorDto.getDireccion())
                .coordenada(coordenadaMapper.mapToEntity(vendedorDto.getCoordenadaDto()))
                .build();
    }
}
