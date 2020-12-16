package org.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT distinct u FROM User u left outer join u.advertenties a")
//@JsonIgnoreProperties({"advertenties"})
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String naam;

    private String password;

    private String email;

    private String adres;

    private String postcode;

    private String woonplaats;

    private boolean isAkkoord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Advertentie> advertenties = new ArrayList<>();

    public User() {
    }

    public User(long id, String naam, String password, String email, String adres, String postcode, String woonplaats, boolean isAkkoord) {
        this.id = id;
        this.naam = naam;
        this.password = password;
        this.email = email;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.isAkkoord = isAkkoord;
    }

    public User(String naam, String password, String email, String adres, String postcode, String woonplaats, boolean isAkkoord) {
        this.naam = naam;
        this.password = password;
        this.email = email;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.isAkkoord = isAkkoord;
    }
}
