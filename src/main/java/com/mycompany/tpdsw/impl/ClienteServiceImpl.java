package com.mycompany.tpdsw.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.mapper.ClienteMapper;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.repository.ClienteRepository;
import com.mycompany.tpdsw.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    /**
     * Obtiene todos los clientes activos en el sistema.
     * 
     * @return Lista de clientes activos.
     */
    @Override
    public List<ClienteDto> findAllActive() {
        List<Cliente> clientes = clienteRepository.findAllActive();
        return clientes.stream().map(clienteMapper::mapToDto).toList();
    }

    /**
     * Busca un cliente activo segun su identificador unico.
     * 
     * @param id Identificador unico del cliente.
     * @return Cliente encontrado o null si no existe.
     */
    @Override
    public ClienteDto findByIdAndActive(Integer id) throws ClienteNoEncontradoException {
        Cliente cliente = clienteRepository.findByIdAndActive(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente " + id + " no encontrado");
        }
        return clienteMapper.mapToDto(cliente);

    }

    /**
     * Registra y persiste un nuevo cliente en el sistema.
     * 
     * @param clienteDto DTO con la informacion del cliente a registrar.
     */
    @Override
    public ClienteDto save(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.mapToEntity(clienteDto);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.mapToDto(clienteGuardado);
    }

    /**
     * Actualiza la informacion de un cliente existente.
     * - Sobrescribe los datos del cliente con los valores proporcionados en el DTO.
     * 
     * @param clienteDto DTO con los datos actualizados del cliente.
     */
    @Override
    public ClienteDto update(ClienteDto clienteDto) throws ClienteNoEncontradoException {
        Cliente cliente = clienteMapper.mapToEntity(clienteDto);

        Integer id = cliente.getId();

        if (!clienteRepository.existsById(id)) {
            throw new ClienteNoEncontradoException("Cliente " + cliente.getId() + " no encontrado");
        }

        cliente = clienteRepository.save(cliente);

        return clienteMapper.mapToDto(cliente);
    }

    /**
     * Elimina logicamente un cliente estableciendo su estado como inactivo.
     * 
     * @param id Identificador unico del cliente a eliminar.
     */
    @Override
    public void delete(ClienteDto clienteDto) throws ClienteNoEncontradoException {

        Cliente cliente = clienteMapper.mapToEntity(clienteDto);

        Integer id = cliente.getId();

        if (!clienteRepository.existsById(id)) {
            throw new ClienteNoEncontradoException("Cliente " + cliente.getId() + " no encontrado");
        }

        clienteRepository.deleteLogico(id);

    }

}
