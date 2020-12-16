package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertentieDto {

    private long id;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private Categorie categorie;

    private String naam;

    private String omschrijving;

    private double prijs;

    private boolean isVerkocht;

    private String userEmail;

    private String userNaam;

    public AdvertentieDto(long id, String naam, double prijs, String omschrijving, boolean isVerkocht) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.isVerkocht = isVerkocht;
    }
}
