package org.example.resource;

import org.example.dao.Dao;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.util.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
public class UserResource extends Resource<User> implements JsonResource {

    public UserDao getDao() { return (UserDao) this.dao; }

    @Path("/login")
    @POST
    public Response login(User u){
        try {
            String email = u.getEmail();
            String password = u.getPassword();

            User currentUser = getDao().login(email, password);
            System.out.println("gelukt");

            return Response.ok( currentUser).build();
        } catch (Exception e) {
            System.out.println("niet gelukt");
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @Override
    @POST
    public Response post(User user) {
        User emailExists = getDao().getUserByEmail(user.getEmail());
        if (emailExists == null) {
            if (dao.add(user)) {
                return Response.ok(user).build();
            } else {
                throw new RuntimeException("Post " + user + " failed.");
            }
        }else{
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @Inject
    public void setDao(Dao<User> dao) { this.dao = dao; }

}
