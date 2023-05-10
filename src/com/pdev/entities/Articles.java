/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.entities;

/**
 *
 * @author aminh
 */
public class Articles {
    
    int id , quantite_article;
    String titre_article , nom_artiste , desc_article,photo_article;
    Float prix_article;

    public Articles() {
    }

    public Articles(int id, int quantite_article, String titre_article, String nom_artiste, String desc_article, Float prix_article) {
        this.id = id;
        this.quantite_article = quantite_article;
        this.titre_article = titre_article;
        this.nom_artiste = nom_artiste;
        this.desc_article = desc_article;
        this.prix_article = prix_article;
    }

    public Articles(int quantite_article, String titre_article, String nom_artiste, String desc_article, Float prix_article) {
        this.quantite_article = quantite_article;
        this.titre_article = titre_article;
        this.nom_artiste = nom_artiste;
        this.desc_article = desc_article;
        this.prix_article = prix_article;
    }

    public Articles(int id, int quantite_article, String titre_article, String nom_artiste, String desc_article, String photo_article, Float prix_article) {
        this.id = id;
        this.quantite_article = quantite_article;
        this.titre_article = titre_article;
        this.nom_artiste = nom_artiste;
        this.desc_article = desc_article;
        this.photo_article = photo_article;
        this.prix_article = prix_article;
    }

    public Articles(int quantite_article, String titre_article, String nom_artiste, String desc_article, String photo_article, Float prix_article) {
        this.quantite_article = quantite_article;
        this.titre_article = titre_article;
        this.nom_artiste = nom_artiste;
        this.desc_article = desc_article;
        this.photo_article = photo_article;
        this.prix_article = prix_article;
    }


    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite_article() {
        return quantite_article;
    }

    public void setQuantite_article(int quantite_article) {
        this.quantite_article = quantite_article;
    }

    public String getTitre_article() {
        return titre_article;
    }

    public void setTitre_article(String titre_article) {
        this.titre_article = titre_article;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getDesc_article() {
        return desc_article;
    }

    public void setDesc_article(String desc_article) {
        this.desc_article = desc_article;
    }

    public Float getPrix_article() {
        return prix_article;
    }

    public void setPrix_article(Float prix_article) {
        this.prix_article = prix_article;
    }

    public String getPhoto_article() {
        return photo_article;
    }

    public void setPhoto_article(String photo_article) {
        this.photo_article = photo_article;
    }
    
    
    
}
