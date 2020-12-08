package org.example.dao;

import org.example.domain.User;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public abstract class Dao<E> {

//    @PersistenceContext(name = "prickAds") // Container managed persistence context
//    protected EntityManager em;   ff vragen aan bram
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("prickAds");
    protected EntityManager em = emf.createEntityManager();

    public Collection<E> getAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E getById(long id){
        return em.createQuery("select e from " + typeSimple() + " e where e.id = :id", E()).setParameter("id",id).getSingleResult();
    }

    public Collection<E> get(String q) {
        return null;
    }

    public boolean add(E c) {
        em.persist(c);
        return true; // Fix me ...
    }

    public void remove(long id) {
        E e = getById(id);
        em.remove(e);
    }

    public boolean update(long id, E c) {
        return false;
    }

    private String typeSimple() { return E().getSimpleName(); }

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
