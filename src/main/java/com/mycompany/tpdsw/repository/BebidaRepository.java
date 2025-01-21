package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Bebida;
import com.mycompany.tpdsw.model.ItemMenu;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Integer> {

    @Query("FROM Bebida p WHERE p.activo = true")
    public List<ItemMenu> findAllActive();

    @Query("FROM Bebida b " +
            "WHERE b.id = :id " +
            "AND b.activo = true")
    public Bebida findActiveBebidaById(@Param("id") Integer id);

    @Query("FROM Bebida b " +
            "WHERE b.activo = true " +
            "AND b.vendedor.id = :idVendedor")
    public List<Bebida> findActiveByIdVendedor(@Param("idVendedor") Integer idVendedor);
}
