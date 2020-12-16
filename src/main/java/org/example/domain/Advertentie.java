package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Advertentie.findAll", query = "SELECT new org.example.domain.AdvertentieDto(a.id, a.naam, a.omschrijving, a.prijs, a.isVerkocht, a.user.email, a.user.naam) FROM Advertentie a"),
        @NamedQuery(name = "Advertentie.findByUser", query = "select new org.example.domain.AdvertentieDto(a.id, a.naam, a.prijs, a.omschrijving, a.isVerkocht) from Advertentie a where a.user.id = :id"),
        @NamedQuery(name = "Advertentie.findById", query = "select new org.example.domain.AdvertentieDto(a.id, a.naam, a.prijs, a.omschrijving, a.isVerkocht) from Advertentie a where a.id = :id")
})
public class Advertentie {

    @Id
    @GeneratedValue
    private long id;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private Categorie categorie;

    private String naam;

    private String omschrijving;

    private double prijs;

    private boolean isVerkocht;
    @ManyToOne
    private User user;

}
