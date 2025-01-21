package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.model.Cliente;

import lombok.Builder;

@Builder
@Component
public class ClienteMapper implements Mapper<Cliente, ClienteDto> {

    @Autowired
    private final CoordenadaMapper coordenadaMapper;

    @Override
    public ClienteDto mapToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .cuit(cliente.getCuit())
                .direccion(cliente.getDireccion())
                .email(cliente.getEmail())
                .coordenadaDto(coordenadaMapper.mapToDto(cliente.getCoordenada()))
                .build();
    }

    @Override
    public Cliente mapToEntity(ClienteDto clienteDto) {
        return Cliente.builder()
                .id(clienteDto.getId())
                .nombre(clienteDto.getNombre())
                .cuit(clienteDto.getCuit())
                .direccion(clienteDto.getDireccion())
                .email(clienteDto.getEmail())
                .coordenada(coordenadaMapper.mapToEntity(clienteDto.getCoordenadaDto()))
                .build();
    }

}
