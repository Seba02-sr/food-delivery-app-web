package com.mycompany.tpdsw.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tpdsw.service.HibernateUtil;

public class GenericDAO<T, ID extends Serializable> {

    private final Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Guarda una entidad en la base de datos.
     * 
     * @param entity Entidad a guardar.
     */
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    /**
     * Actualiza una entidad existente en la base de datos.
     * 
     * @param entity Entidad a actualizar.
     */
    @SuppressWarnings("deprecation")
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Actualizar la entidad directamente
            session.update(entity); // Hibernate buscará la entidad por el ID y la actualizará si existe
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al actualizar la entidad", e);
        }
    }

    /**
     * Elimina una entidad de la base de datos.
     * 
     * @param entity Entidad a eliminar.
     */
    @SuppressWarnings("deprecation")
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }

    /**
     * Busca una entidad por su ID.
     * 
     * @param id ID de la entidad.
     * @return Entidad encontrada o null si no existe.
     */
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(persistentClass, id);
        }
    }

    /**
     * Obtiene todas las entidades de la tabla.
     * 
     * @return Lista de entidades.
     */
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + persistentClass.getName();
            return session.createQuery(hql, persistentClass).list();
        }
    }

    @SuppressWarnings("deprecation")
    public void deleteLogico(T entity) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            transaction = session.beginTransaction();
            // Se asume que hay un método setActivo en la entidad
            persistentClass.getMethod("setActivo", Boolean.class).invoke(entity, false);
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar (lógicamente) la entidad", e);
        }
    }

    public List<T> findAllActive() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + persistentClass.getName() + " WHERE activo = true";
            return session.createQuery(hql, persistentClass).list();
        }
    }

    /**
     * Busca una entidad por su ID y verifica si está activa.
     * 
     * @param id ID de la entidad.
     * @return La entidad activa o null si no está activa.
     */
    public T findByIdAndActive(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            T entity = session.get(persistentClass, id);
            if (entity != null) {
                Method isActivoMethod = persistentClass.getMethod("getActivo");
                isActivoMethod.setAccessible(true);
                Boolean activo = (Boolean) isActivoMethod.invoke(entity);
                if (activo == null || !activo) {
                    return null; // Si no está activo o es null, retorna null
                }
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la entidad activa", e);
        }
    }
}