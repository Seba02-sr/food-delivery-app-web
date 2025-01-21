package com.mycompany.tpdsw.dao;

import java.util.List;

import org.hibernate.Session;

import com.mycompany.tpdsw.exception.CategoriaNoEncontradaException;
import com.mycompany.tpdsw.model.Categoria;
import com.mycompany.tpdsw.model.TipoCategoria;
import com.mycompany.tpdsw.service.HibernateUtil;

public class CategoriaDao extends GenericDAO<Categoria, Integer> {

        public CategoriaDao() {
                super(Categoria.class);
        }

        public Categoria findByNombre(String nombre) {
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                        String hql = "FROM Categoria c " +
                                        "WHERE c.nombre = :nombre ";

                        Categoria categoria = session.createQuery(hql, Categoria.class)
                                        .setParameter("nombre", nombre)
                                        .uniqueResult();

                        if (categoria == null) {
                                throw new CategoriaNoEncontradaException(
                                                "No se ha encontrado una categoria con nombre: " + nombre);
                        }
                        return categoria;

                } catch (Exception e) {
                        String errorMessage = "Error al intentar recuperar la categoria con nombre: " + nombre;
                        throw new RuntimeException(errorMessage, e);
                }
        }

        public List<Categoria> findByTipoCategoria(String tipo) throws CategoriaNoEncontradaException {
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                        String hql = "FROM Categoria c " +
                                        "WHERE c.tipo = :tipo ";

                        TipoCategoria tipoCategoria = TipoCategoria.valueOf(tipo.toUpperCase());
                        List<Categoria> categoria = session.createQuery(hql, Categoria.class)
                                        .setParameter("tipo", tipoCategoria)
                                        .getResultList();

                        if (categoria.isEmpty()) {
                                throw new CategoriaNoEncontradaException(
                                                "No se ha encontrado una categoria del tipo: " + tipo);
                        }
                        return categoria;

                } catch (IllegalArgumentException e) {
                        throw new CategoriaNoEncontradaException("Tipo de categoría no válido: " + tipo);
                } catch (Exception e) {
                        String errorMessage = "Error al intentar recuperar la categoria del tipo: " + tipo;
                        throw new RuntimeException(errorMessage, e);
                }

        }
}