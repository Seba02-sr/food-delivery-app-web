package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Vendedor;

import jakarta.transaction.Transactional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

        // Busca vendedor que contenga el nombre
        @Query("FROM Vendedor v " +
                        "WHERE v.activo = true " +
                        "AND LOWER(v.nombre) LIKE :nombre")
        public List<Vendedor> findActiveByNombre(@Param("nombre") String nombre);

        // Busca vendedores activos y trae consigo los item del mismo
        @Query("SELECT v FROM Vendedor v " +
                        "LEFT JOIN FETCH v.itemsMenu " +
                        "WHERE v.id = :id " +
                        "AND v.activo = true")
        public Vendedor findActiveByIdWithItemsMenu(@Param("id") Integer id);

        // Encuentra todos los vendedores activos
        List<Vendedor> findAllByActivoTrue();

        // Busca vendedores por id y activo
        Vendedor findByIdAndActivoTrue(Integer id);

        // Eliminacion logica de un vendedor
        @Transactional
        @Modifying
        @Query("UPDATE Vendedor v SET v.activo = false WHERE v.id = :id")
        void deleteLogico(@Param("id") Integer id);

}
