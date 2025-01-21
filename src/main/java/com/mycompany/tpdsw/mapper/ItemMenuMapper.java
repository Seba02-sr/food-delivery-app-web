package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.BebidaDto;
import com.mycompany.tpdsw.dto.ItemMenuDto;
import com.mycompany.tpdsw.dto.PlatoDto;
import com.mycompany.tpdsw.exception.VendedorNoEncontradoException;
import com.mycompany.tpdsw.model.Bebida;
import com.mycompany.tpdsw.model.Categoria;
import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.model.Plato;
import com.mycompany.tpdsw.model.Vendedor;
import com.mycompany.tpdsw.service.CategoriaService;
import com.mycompany.tpdsw.service.VendedorService;

import lombok.Builder;

@Builder
@Component
public class ItemMenuMapper implements Mapper<ItemMenu, ItemMenuDto> {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VendedorService vendedorService;

    private CategoriaMapper categoriaMapper;
    private VendedorMapper vendedorMapper;

    @Override
    public ItemMenuDto mapToDto(ItemMenu entity) {
        switch (entity.getClass().getSimpleName().toLowerCase()) {
            case "plato":
                Plato plato = (Plato) entity;
                return PlatoDto.builder()
                        .id(plato.getId())
                        .nombre(plato.getNombre())
                        .descripcion(plato.getDescripcion())
                        .precio(plato.getPrecio())
                        .categoria(plato.getCategoria().getNombre())
                        .idVendedor(plato.getVendedor().getId())
                        .calorias(plato.getCalorias())
                        .aptoCeliaco(plato.getAptoCeliaco())
                        .aptoVegetariano(plato.getAptoVegetariano())
                        .aptoVegano(plato.getAptoVegano())
                        .peso(plato.getPeso())
                        .build();
            case "bebida":
                Bebida bebida = (Bebida) entity;
                return BebidaDto.builder()
                        .id(bebida.getId())
                        .nombre(bebida.getNombre())
                        .descripcion(bebida.getDescripcion())
                        .precio(bebida.getPrecio())
                        .categoria(bebida.getCategoria().getNombre())
                        .idVendedor(bebida.getVendedor().getId())
                        .graduacionAlcoholica(bebida.getGraduacionAlcoholica())
                        .tamano(bebida.getTamano())
                        .volumen(bebida.getVolumen())
                        .build();

            default:
                return null;
        }
    }

    @Override
    public ItemMenu mapToEntity(ItemMenuDto dto) {

        Categoria categoria = categoriaMapper.mapToEntity(categoriaService.findByNombre(dto.getCategoria()));
        Vendedor vendedor = null;
        try {
            vendedor = vendedorMapper.mapToEntity(vendedorService.findById(dto.getId()));
        } catch (VendedorNoEncontradoException e) {
            return null;
        }
        switch (dto.getClass().getSimpleName().toLowerCase()) {
            case "platodto":
                PlatoDto platoDto = (PlatoDto) dto;
                return Plato.builder()
                        .id(platoDto.getId())
                        .nombre(platoDto.getNombre())
                        .descripcion(platoDto.getDescripcion())
                        .precio(platoDto.getPrecio())
                        .categoria(categoria)
                        .vendedor(vendedor)
                        .calorias(platoDto.getCalorias())
                        .aptoCeliaco(platoDto.getAptoCeliaco())
                        .aptoVegetariano(platoDto.getAptoVegetariano())
                        .aptoVegano(platoDto.getAptoVegano())
                        .peso(platoDto.getPeso())
                        .build();
            case "bebidadto":
                BebidaDto bebidaDto = (BebidaDto) dto;
                return Bebida.builder()
                        .id(bebidaDto.getId())
                        .nombre(bebidaDto.getNombre())
                        .descripcion(bebidaDto.getDescripcion())
                        .precio(bebidaDto.getPrecio())
                        .categoria(categoria)
                        .vendedor(vendedor)
                        .graduacionAlcoholica(bebidaDto.getGraduacionAlcoholica())
                        .tamano(bebidaDto.getTamano())
                        .volumen(bebidaDto.getVolumen())
                        .build();

            default:
                return null;
        }
    }

}
