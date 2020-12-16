package org.example.resource;

import org.example.dao.Dao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public abstract class Resource<E> {

    protected Dao<E> dao;

    @GET
    public Collection<E> getAll(){
        return dao.getAll();
    }

    @Path("{id}") @GET
    public E get(@PathParam("id") long id) {
        return dao.getById(id);
    }

    @POST
    public Response post(E e) {
        if (dao.add(e)) {
                return Response.ok(e).build();
        } else {
            throw new RuntimeException("Post " + e + " failed.");
        }
    }

    @Path("{id}") @DELETE
    public Response delete(@PathParam("id") long id) {
        try{
            dao.remove(id);
            return Response.ok(id).build();
        }catch(Exception e){
            return Response.status(UNAUTHORIZED).build();
        }
    }

}
