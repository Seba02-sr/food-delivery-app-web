/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tpdsw.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;

import com.mycompany.tpdsw.exception.ItemNoEncontradoException;
import com.mycompany.tpdsw.model.ItemPedido;
import com.mycompany.tpdsw.service.HibernateUtil;

/**
 *
 * @author User
 */
public class ItemsPedidoDao extends GenericDAO<ItemPedido, Integer> {

    public ItemsPedidoDao() {
        super(ItemPedido.class);
    }

    public List<ItemPedido> findByIdRestaurante(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ip FROM ItemPedido ip " +
                    "JOIN ip.itemMenu im " +
                    "WHERE im.vendedor.id = :idRestaurante " +
                    "AND im.vendedor.activo = true";

            List<ItemPedido> itemspedidos = session.createQuery(hql, ItemPedido.class)
                    .setParameter("idRestaurante", id)
                    .getResultList();

            if (itemspedidos.isEmpty()) {
                throw new ItemNoEncontradoException("No se encontraron item pedidos al vendedor con ID:" + id);
            }
            return itemspedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los items pedidos al vendedor con ID: " + id;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<ItemPedido> findSortedByPrecio() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ip FROM ItemPedido ip " +
                    "JOIN ip.itemMenu im " +
                    "ORDER BY im.precio ASC";

            List<ItemPedido> itemPedidos = session.createQuery(hql, ItemPedido.class)
                    .getResultList();

            if (itemPedidos.isEmpty()) {
                throw new ItemNoEncontradoException("No se encontrarion item Pedidos para ordenar");
            }
            return itemPedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los ItemPedidos ordenados por precio";
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<ItemPedido> findBetweenPrecios(BigDecimal precioMin, BigDecimal precioMax) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ip FROM ItemPedido ip " +
                    "JOIN ip.itemMenu im " +
                    "WHERE im.precio BETWEEN :precioMin AND :precioMax";

            List<ItemPedido> itemPedidos = session.createQuery(hql, ItemPedido.class)
                    .setParameter("precioMin", precioMin)
                    .setParameter("precioMax", precioMax)
                    .getResultList();
            if (itemPedidos.isEmpty()) {
                throw new ItemNoEncontradoException(
                        "No se encontrarion item Pedidos en el rango: [" + precioMin + "-" + precioMax + "]");
            }
            return itemPedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los ItemPedidos entre los precios " + precioMin + " y "
                    + precioMax;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<ItemPedido> findByNombreRestaurante(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ip FROM ItemPedido ip " +
                    "JOIN ip.itemMenu im " +
                    "WHERE im.vendedor.nombre LIKE :nombre";

            List<ItemPedido> itemspedidos = session.createQuery(hql, ItemPedido.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();

            if (itemspedidos.isEmpty()) {
                throw new ItemNoEncontradoException("No se encontraron item pedidos al vendedor con Nombre:" + nombre);
            }
            return itemspedidos;

        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los items pedidos al vendedor con nombre: " + nombre;
            throw new RuntimeException(errorMessage, e);
        }
    }
}
