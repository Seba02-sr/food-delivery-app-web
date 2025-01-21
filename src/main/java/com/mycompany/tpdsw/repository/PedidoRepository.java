package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Estado;
import com.mycompany.tpdsw.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

        @Query("SELECT DISTINCT p FROM Pedido p " +
                        "JOIN FETCH p.pedidoItemPedidos pip " +
                        "JOIN FETCH pip.itemPedido ip " +
                        "JOIN FETCH ip.itemMenu im " +
                        "JOIN FETCH im.vendedor v " +
                        "WHERE p.cliente.id = :idCliente " +
                        "AND p.cliente.activo = true")
        public List<Pedido> findByCliente(@Param("idCliente") Integer idCliente);

        @Query("FROM Pedido p " +
                        "WHERE p.estado = :estado ")
        public List<Pedido> findByEstado(@Param("estado") Estado estado);

        @Query("SELECT p FROM Pedido p " +
                        "JOIN FETCH p.pedidoItemPedidos pip " +
                        "JOIN FETCH pip.itemPedido ip " +
                        "JOIN FETCH ip.itemMenu im " +
                        "WHERE im.vendedor.activo = true " +
                        "AND im.vendedor.id = :idVendedor")
        public List<Pedido> findByIdVendedor(@Param("idVendedor") Integer idVendedor);

        @Query("SELECT p FROM Pedido p " +
                        "JOIN FETCH p.pedidoItemPedidos pip " +
                        "JOIN FETCH pip.itemPedido ip " +
                        "WHERE p.id = :id ")
        public Pedido findPedidoByIdWithItem(@Param("id") Integer id);
}
