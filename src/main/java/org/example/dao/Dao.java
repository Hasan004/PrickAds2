package org.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public abstract class Dao<E> {

    @PersistenceContext
    EntityManager em;

    public Collection<E> getAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E getById(long id) {
        return em.createQuery("select e from " + typeSimple() + " e where e.id = :id", E()).setParameter("id", id).getSingleResult();
    }

    public boolean add(E c) {
        try {
            em.persist(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void remove(long id) {
        E e = getById(id);
        em.remove(e);
    }

    private String typeSimple() {
        return E().getSimpleName();
    }

    /**
     * @return a class instance of the first generic type parameter (E) of this Dao,
     * e.g. for EmployeeDao<Employee, Long>, it returns Employee.class.
     */
    @SuppressWarnings("unchecked")
    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }

}
