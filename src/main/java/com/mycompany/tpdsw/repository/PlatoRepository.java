package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

    @Query("FROM Plato p " +
            "WHERE p.id = :id " +
            "AND p.activo = true")
    public Plato findActivePlatoById(Integer id);

    @Query("FROM Plato p " +
            "WHERE p.activo = true " +
            "AND p.vendedor.activo = true " +
            "AND p.vendedor.id = :id")
    List<Plato> findActiveByIdVendedor(@Param("id") Integer id);
}
