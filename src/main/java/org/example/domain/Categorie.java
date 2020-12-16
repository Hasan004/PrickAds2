//package org.example.domain;
//
//import lombok.Data;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//@NamedQuery(name = "Categorie.findAll", query = "SELECT c from Categorie c where c.id = :id")
//public class Categorie {
//
//    @Id
//    private long id;
//
//    private String naam;
//
//    @OneToMany(mappedBy = "categorie")
//    private List<Advertentie> advertenties = new ArrayList<>();;
//
//    public Categorie() {
//    }
//
//    public Categorie(String naam) {
//        this.naam = naam;
//    }
//
//    public Categorie(long id, String naam) {
//        this.id = id;
//        this.naam = naam;
//    }
//
//    public String getNaam() {
//        return naam;
//    }
//
//    public void setNaam(String naam) {
//        this.naam = naam;
//    }
//
//
//}
