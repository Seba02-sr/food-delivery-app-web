package com.mycompany.tpdsw.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mycompany.tpdsw.exception.ItemNoEncontradoException;
import com.mycompany.tpdsw.model.Bebida;
import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.service.HibernateUtil;

public class BebidaDao extends ItemMenuDao {

    // Sobrescribir findAll para devolver List<ItemMenu>, pero solo con objetos
    // Bebida
    @Override
    public List<ItemMenu> findAllActive() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Bebida p " +
                    "WHERE p.activo = true";
            List<ItemMenu> itemsMenu = session.createQuery(hql, ItemMenu.class).getResultList();

            // Filtrar para devolver solo los objetos Bebida
            List<ItemMenu> bebidas = new ArrayList<>();
            for (ItemMenu item : itemsMenu) {
                if (item instanceof Bebida) {
                    bebidas.add(item);
                }
            }

            if (bebidas.isEmpty()) {
                throw new ItemNoEncontradoException("No se ha encontrado bebidas");
            }
            return bebidas;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar todas las bebidas";
            throw new RuntimeException(errorMessage, e);
        }
    }

    public Bebida findActiveBebidaById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Bebida b " +
                    "WHERE b.id = :id " +
                    "AND b.activo = true";
            Bebida bebida = session.createQuery(hql, Bebida.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (bebida == null) {
                throw new ItemNoEncontradoException("No se ha encontrado una bebida con id: " + id);
            }
            return bebida;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar la bebida con id: " + id;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<Bebida> findActiveByIdVendedor(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Bebida b " +
                    "WHERE b.activo = true " +
                    "AND b.vendedor.id = :idVendedor";

            List<Bebida> bebidas = session.createQuery(hql, Bebida.class)
                    .setParameter("idVendedor", id)
                    .getResultList();

            if (bebidas.isEmpty()) {
                throw new ItemNoEncontradoException("No se han encontrado bebidas para el vendedor con id: " + id);
            }
            return bebidas;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar las bebidas del vendedor con id: " + id;
            throw new RuntimeException(errorMessage, e);
        }
    }

}
