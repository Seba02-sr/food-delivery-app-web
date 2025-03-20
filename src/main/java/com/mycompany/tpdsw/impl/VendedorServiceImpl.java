package com.mycompany.tpdsw.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.controller.VendedorController;
import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.exception.VendedorNoEncontradoException;
import com.mycompany.tpdsw.mapper.VendedorMapper;
import com.mycompany.tpdsw.model.Vendedor;
import com.mycompany.tpdsw.repository.VendedorRepository;
import com.mycompany.tpdsw.service.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendedorMapper vendedorMapper;

    /**
     * Obtiene todos los vendedores activos en el sistema.
     * 
     * @return Lista de vendedores activos.
     */
    @Override
    public List<VendedorDto> findAllByActivoTrue() {
        List<Vendedor> vendedores = vendedorRepository.findAllByActivoTrue();
        return vendedores.stream().map(vendedorMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Busca un vendedor activo por su ID.
     * 
     * @param id El ID del vendedor a buscar.
     * @return El objeto Vendedor encontrado, o null si no existe un vendedor con
     *         ese ID.
     */
    @Override
    public VendedorDto findById(Integer id) throws VendedorNoEncontradoException {
        Optional<Vendedor> vendedorOpt = Optional.of(vendedorRepository.findByIdAndActivoTrue(id));

        if (!vendedorOpt.isPresent()) {
            throw new VendedorNoEncontradoException("Vendedor " + id + " no encontrado");
        }

        Vendedor vendedor = vendedorOpt.get();
        return vendedorMapper.mapToDto(vendedor);
    }

    /**
     * Busca vendedores activos por nombre.
     * 
     * @param nombre El texto a buscar dentro de los nombres de los vendedores.
     * @return Una lista de vendedores activos que coincidan con el criterio de
     *         busqueda.
     */
    @Override
    public List<VendedorDto> findByNombre(String nombre) throws VendedorNoEncontradoException {
        List<Vendedor> vendedores = vendedorRepository.findActiveByNombre(nombre);

        logger.info("Vendedores encontrados: " + vendedores);
        if (vendedores.isEmpty()) {
            throw new VendedorNoEncontradoException("Vendedor " + nombre + " no encontrado");
        }
        return vendedores.stream().map(vendedorMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Registra un nuevo vendedor en el sistema.
     * Convierte el DTO recibido en objeto dominio y se persiste.
     * 
     * @param vendedorDto El objeto DTO que contiene los datos del vendedor a
     *                    registrar.
     */
    @Override
    public VendedorDto save(VendedorDto vendedorDto) {
        Vendedor vendedor = vendedorMapper.mapToEntity(vendedorDto);
        Vendedor vendedorGuardado = vendedorRepository.save(vendedor);
        return vendedorMapper.mapToDto(vendedorGuardado);
    }

    /**
     * Realiza la eliminacion logica de un vendedor.
     * Marca el vendedor como inactivo en lugar de eliminarlo fisicamente.
     * 
     * @param id El ID del vendedor a eliminar.
     * @throws VendedorNoEncontradoException Si el vendedor no existe o ya esta
     *                                       eliminado.
     */
    @Override
    public void delete(VendedorDto vendedorDto) throws VendedorNoEncontradoException {
        Vendedor vendedor = vendedorMapper.mapToEntity(vendedorDto);

        Integer id = vendedor.getId();

        if (!vendedorRepository.existsById(id)) {
            throw new VendedorNoEncontradoException("Vendedor " + vendedor.getId() + " no encontrado");
        }

        vendedorRepository.deleteLogico(id);
    }

    /**
     * Modifica los datos de un vendedor especifico.
     * Reemplaza los datos persistidos, por los del objeto DTO pasado por parametro.
     * 
     * @param vendedorDto El objeto DTO con los datos del vendedor a modificar.
     */
    @Override
    public VendedorDto update(VendedorDto vendedorDto) throws VendedorNoEncontradoException {
        Vendedor vendedor = vendedorMapper.mapToEntity(vendedorDto);

        Integer id = vendedor.getId();

        if (!vendedorRepository.existsById(id)) {
            throw new VendedorNoEncontradoException("Vendedor " + vendedor.getId() + " no encontrado");
        }

        vendedor = vendedorRepository.save(vendedor);

        return vendedorMapper.mapToDto(vendedor);
    }

}
