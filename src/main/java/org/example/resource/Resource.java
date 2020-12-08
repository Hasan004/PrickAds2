package org.example.resource;

import org.example.dao.Dao;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.List;

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
    public E post(E e) {
        if (dao.add(e)) {
            return e;
        } else {
            throw new RuntimeException("Post " + e + " failed.");
        }
    }

    @Path("{id}") @DELETE
    public void delete(@PathParam("id") long id) {
        try{
            dao.remove(id);
        }catch(Exception e){
            throw new BadRequestException("Delete with id " + id + " failed.");
        }
    }

    @Path("{id}") @PUT
    public E put(@PathParam("id") long id, E e) {
        if (dao.update(id, e)) {
            return e;
        } else {
            throw new RuntimeException("Update " + e + " failed.");
        }
    }
}
