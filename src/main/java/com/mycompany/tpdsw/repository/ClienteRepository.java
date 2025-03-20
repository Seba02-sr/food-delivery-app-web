package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Cliente;

import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Buscar clientes activos por nombre
    @Query("FROM Cliente c WHERE c.activo = true AND c.nombre LIKE %:nombre%")
    List<Cliente> findActiveByNombre(@Param("nombre") String nombre);

    // Buscar cliente activo por ID
    @Query("FROM Cliente c WHERE c.id = :id AND c.activo = true")
    Cliente findByIdAndActive(@Param("id") Integer id);

    // Obtener todos los clientes activos
    @Query("FROM Cliente c WHERE c.activo = true")
    List<Cliente> findAllActive();

    // Eliminación lógica de un cliente
    @Transactional
    @Modifying
    @Query("UPDATE Cliente c SET c.activo = false WHERE c.id = :id")
    void deleteLogico(@Param("id") Integer id);
}
