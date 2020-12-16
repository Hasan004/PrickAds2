package org.example.dao;

import org.example.domain.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UserDao extends Dao<User> {

    public User login(String email, String password) {
        User userFoundByEmail = getUserByEmail(email);
        if (BCrypt.checkpw(password, userFoundByEmail.getPassword())) {
            System.out.println("inloggen succesvol");
            return userFoundByEmail;
        } else {
            System.out.println("inloggen niet gelukt!");
            throw new NoResultException();
        }
    }

    @Override
    public boolean add(User c) {
        try {
            c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
//            try {
//                mailSender.sendMail(c.getEmail(), c.getNaam());
//            } catch (MessagingException e) {
//                System.out.println("emailen niet gelukt, message: " + e.getMessage());
//            }
            System.out.println(c.getPassword());
            em.persist(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserByEmail(String email){
        try { return em.createQuery("select e from User e where e.email = :email", User.class).setParameter("email", email).getSingleResult();}
        catch(NoResultException e){
            System.out.println("Geen email gevonden");
            return null;
        }catch(Exception e){
            System.out.println("something went wrond");
            return null;
        }
    }

}
