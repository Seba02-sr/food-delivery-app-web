package com.mycompany.tpdsw.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

        @Query("SELECT ip FROM ItemPedido ip " +
                        "JOIN ip.itemMenu im " +
                        "WHERE im.vendedor.id = :idRestaurante " +
                        "AND im.vendedor.activo = true")
        public List<ItemPedido> findByIdRestaurante(@Param("idRestaurante") Integer idRestaurante);

        @Query("SELECT ip FROM ItemPedido ip " +
                        "JOIN ip.itemMenu im " +
                        "ORDER BY im.precio ASC")
        public List<ItemPedido> findSortedByPrecio();

        @Query("SELECT ip FROM ItemPedido ip " +
                        "JOIN ip.itemMenu im " +
                        "WHERE im.precio BETWEEN :precioMin AND :precioMax")
        public List<ItemPedido> findBetweenPrecios(@Param("precioMin") BigDecimal precioMin,
                        @Param("precioMax") BigDecimal precioMax);

        @Query("SELECT ip FROM ItemPedido ip " +
                        "JOIN ip.itemMenu im " +
                        "WHERE im.vendedor.nombre LIKE :nombre")
        public List<ItemPedido> findByNombreRestaurante(@Param("nombre") String nombre);
}
