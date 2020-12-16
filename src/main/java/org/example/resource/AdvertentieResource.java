package org.example.resource;

import org.example.dao.AdvertentieDao;
import org.example.dao.Dao;
import org.example.domain.Advertentie;
import org.example.domain.AdvertentieDto;
import org.example.util.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

@Path("/advertentie")
public class AdvertentieResource extends Resource<Advertentie> implements JsonResource {

    public AdvertentieDao getDao() { return (AdvertentieDao) this.dao; }

    @GET
    public Collection<AdvertentieDto> getAllAds(){
        return getDao().getAllAds();
    }

    @Path("/userads/{id}")
    @GET
    public Collection<AdvertentieDto> getAllAdsByUserId(@PathParam("id") long id){
        try{
            return getDao().getAllAdsByUserId(id);
        }catch(Exception e){
            System.out.println("advertentie niet gevonden");
            return null;
        }
    }

    @Path("/ads/{id}")
    @GET
    public AdvertentieDto getAdsByUserId(@PathParam("id") long id){
        try{
            return getDao().getAdById(id);
        }catch(Exception e){
            return null;
        }
    }

    @Path("{id}") @PUT
    public Advertentie put(@PathParam("id") long id, Advertentie e) {
        try{
            getDao().update(id, e);
            return e;
        }catch(Exception exc) {
            throw new RuntimeException("Update " + exc + " failed.");
        }
    }

    @Inject
    public void setDao(Dao<Advertentie> dao) { this.dao = dao; }
}
