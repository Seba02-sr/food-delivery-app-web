package com.mycompany.tpdsw.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mycompany.tpdsw.model.Vendedor;
import com.mycompany.tpdsw.service.HibernateUtil;

public class VendedorDao extends GenericDAO<Vendedor, Integer> {

    public VendedorDao() {
        super(Vendedor.class);
    }

    public List<Vendedor> findActiveByNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Crear la consulta HQL
            String hql = "FROM Vendedor v " +
                    "WHERE v.activo = true " +
                    "AND LOWER(v.nombre) LIKE :nombre";
            Query<Vendedor> query = session.createQuery(hql, Vendedor.class);

            // Asignar el parámetro de búsqueda, asegurándose de que se busque de forma
            // insensible a mayúsculas
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");

            // Ejecutar la consulta y devolver los resultados
            List<Vendedor> resultados = query.list();

            return resultados;
        } catch (Exception e) {
            String errorMessage = "Error al intentar recuperar el vendedor con el nombre: " + nombre;
            throw new RuntimeException(errorMessage, e);
        }

    }

    public Vendedor findActiveByIdWithItemsMenu(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT v FROM Vendedor v " +
                    "LEFT JOIN FETCH v.itemsMenu " +
                    "WHERE v.id = :id " +
                    "AND v.activo = true";
            Query<Vendedor> query = session.createQuery(hql, Vendedor.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            String errorMessage = "Error al buscar el vendedor con sus itemsMenu por ID: " + id;
            throw new RuntimeException(errorMessage, e);
        }
    }

    // Delete --> Usar deleteLogico
    // FindById --> Usar findByIdAndActive
    // FindAll --> Usar findAllActive

}
