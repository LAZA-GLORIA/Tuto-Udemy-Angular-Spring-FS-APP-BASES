package com.demo.udemy.bouali.domain;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    private String nomCat;
    private String descriptionCat;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public Categorie(Long idCat, String nomCat, String descriptionCat) {
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCat=" + idCat +
                ", nomCat='" + nomCat + '\'' +
                ", descriptionCat='" + descriptionCat + '\'' +
                '}';
    }
}