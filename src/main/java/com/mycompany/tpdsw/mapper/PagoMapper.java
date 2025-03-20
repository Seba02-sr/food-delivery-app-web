package com.mycompany.tpdsw.mapper;

import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.MercadoPagoDto;
import com.mycompany.tpdsw.dto.PagoDto;
import com.mycompany.tpdsw.dto.TransferenciaDto;
import com.mycompany.tpdsw.model.MercadoPago;
import com.mycompany.tpdsw.model.Pago;
import com.mycompany.tpdsw.model.Transferencia;

import lombok.Builder;

@Component
@Builder
public class PagoMapper implements Mapper<Pago, PagoDto> {

    @Override
    public PagoDto mapToDto(Pago entity) {

        switch (entity.getClass().getSimpleName().toLowerCase()) {
            case "mercadopago":
                MercadoPago mp = (MercadoPago) entity;
                return MercadoPagoDto.builder()
                        .id(mp.getId())
                        .fechaPago(mp.getFechaPago())
                        .monto(mp.getMonto())
                        .alias(mp.getAlias())
                        .build();
            case "transferencia":
                Transferencia transferencia = (Transferencia) entity;
                return TransferenciaDto.builder()
                        .id(transferencia.getId())
                        .fechaPago(transferencia.getFechaPago())
                        .monto(transferencia.getMonto())
                        .cbu(transferencia.getCbu())
                        .cuit(transferencia.getCuit())
                        .build();
            default:
                return null;
        }
    }

    @Override
    public Pago mapToEntity(PagoDto dto) {
        switch (dto.getClass().getSimpleName().toLowerCase()) {
            case "mercadopagodto":
                MercadoPagoDto mp = (MercadoPagoDto) dto;
                return MercadoPago.builder()
                        .id(mp.getId())
                        .fechaPago(mp.getFechaPago())
                        .monto(mp.getMonto())
                        .alias(mp.getAlias())
                        .build();
            case "transferenciadto":
                TransferenciaDto transferencia = (TransferenciaDto) dto;
                return Transferencia.builder()
                        .id(transferencia.getId())
                        .fechaPago(transferencia.getFechaPago())
                        .monto(transferencia.getMonto())
                        .cbu(transferencia.getCbu())
                        .cuit(transferencia.getCuit())
                        .build();
            default:
                return null;
        }
    }

}
