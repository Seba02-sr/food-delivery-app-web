package com.mycompany.tpdsw.repository;

import com.mycompany.tpdsw.model.ItemMenu;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenu, Integer> {

        // Encuentra todos los ítems activos
        List<ItemMenu> findAllByActivoTrue();

        // Encuentra un ítem por su ID si está activo
        @Query("SELECT im FROM ItemMenu im WHERE im.id = :id AND im.activo = true")
        ItemMenu findByIdAndActivo(@Param("id") Integer id);

        // Busca ítems activos por nombre
        @Query("SELECT im FROM ItemMenu im WHERE im.nombre LIKE %:nombre% AND im.activo = true")
        List<ItemMenu> findActiveByNombre(@Param("nombre") String nombre);

        // Busca ítems activos por nombre y vendedor
        @Query("SELECT im FROM ItemMenu im " +
                        "WHERE im.nombre LIKE %:nombre% AND im.activo = true " +
                        "AND im.vendedor.id = :vendedorId AND im.vendedor.activo = true")
        List<ItemMenu> findActiveByNombreAndVendedorId(@Param("nombre") String nombre,
                        @Param("vendedorId") Integer vendedorId);

        // Encuentra ítems por el ID del vendedor
        @Query("SELECT im FROM ItemMenu im " +
                        "WHERE im.vendedor.id = :vendedorId " +
                        "AND im.vendedor.activo = true " +
                        "AND im.activo = true")
        List<ItemMenu> findActiveByVendedorId(@Param("vendedorId") Integer vendedorId);

        // Eliminacion logica de un item menu
        @Transactional
        @Modifying
        @Query("UPDATE ItemMenu im SET im.activo = false WHERE im.id = :id")
        void deleteLogico(@Param("id") Integer id);
}
