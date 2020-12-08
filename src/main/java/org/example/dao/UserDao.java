package org.example.dao;

import org.example.domain.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao extends Dao<User> {

    public boolean login(String email, String password){
        TypedQuery<User> query = em.createQuery("select e from User e where e.email = :email and e.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password",password);
        User user = query.getSingleResult();

        return user == null;
    }

}
