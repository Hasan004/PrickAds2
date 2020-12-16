package org.example.dao;

import org.example.domain.Advertentie;
import org.example.domain.AdvertentieDto;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import java.util.Collection;

@Stateless
public class AdvertentieDao extends Dao<Advertentie> {

    public Collection<AdvertentieDto> getAllAds() {
        TypedQuery<AdvertentieDto> query = em.createNamedQuery("Advertentie.findAll", AdvertentieDto.class);
        return query.getResultList();
    }

    public Collection<AdvertentieDto> getAllAdsByUserId(long currentUserId) {
        return em.createNamedQuery("Advertentie.findByUser", AdvertentieDto.class).setParameter("id", currentUserId).getResultList();
    }

    public AdvertentieDto getAdById(long id) {
        return em.createNamedQuery("Advertentie.findById", AdvertentieDto.class).setParameter("id", id).getSingleResult();
    }

    public Advertentie update(long id, Advertentie e) {
        Advertentie found = em.find(Advertentie.class, id);
        if (found == null) throw new BadRequestException("Entity with id " + id + " not found.");
        e.setId(id);
        return em.merge(e);
    }


}
