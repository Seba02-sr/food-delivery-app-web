package com.mycompany.tpdsw.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mycompany.tpdsw.exception.ItemNoEncontradoException;
import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.model.Plato;
import com.mycompany.tpdsw.service.HibernateUtil;

public class PlatoDao extends ItemMenuDao {

    public Plato findActivePlatoById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Plato p " +
                    "WHERE p.id = :id " +
                    "AND p.activo = true";
            Plato plato = session.createQuery(hql, Plato.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (plato == null) {
                throw new ItemNoEncontradoException("No se ha encontrado el plato con id: " + id);
            }
            return plato;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar un plato con id: " + id;
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<Plato> findActiveByIdVendedor(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Plato p " +
                    "WHERE p.activo = true " +
                    "AND p.vendedor.activo = true " +
                    "AND p.vendedor.id = :id";

            List<Plato> platos = session.createQuery(hql, Plato.class)
                    .setParameter("id", id)
                    .getResultList();

            if (platos.isEmpty()) {
                return null;
            }
            return platos;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar los platos del vendedor con id: " + id;
            throw new RuntimeException(errorMessage, e);
        }

    }

    // Sobrescribir findAll para devolver List<ItemMenu>, pero solo con objetos
    // Plato
    @Override
    public List<ItemMenu> findAllActive() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Plato p " +
                    "WHERE p.activo = true";
            List<ItemMenu> itemsMenu = session.createQuery(hql, ItemMenu.class).getResultList();

            // Filtrar para devolver solo los objetos Plato
            List<ItemMenu> platos = new ArrayList<>();
            for (ItemMenu item : itemsMenu) {
                if (item instanceof Plato) {
                    platos.add(item);
                }
            }

            if (platos.isEmpty()) {
                throw new ItemNoEncontradoException("No se ha encontrado platos");
            }
            return platos;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar todos los platos";
            throw new RuntimeException(errorMessage, e);
        }
    }
}
