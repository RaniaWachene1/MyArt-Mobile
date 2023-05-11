/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    private int idr ; 
    private String titre ; 
    private String description ; 
    private String image ; 
    private Date dater ;
    private String etat;
    private int id_user ; 
    private int id_typer  ;

    public Reclamation(String titre, String description, String image,String etat, Date dater, int id_user, int id_typer) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.dater = dater;
        this.id_user = id_user;
        this.id_typer = id_typer;
        this.etat=etat;
    }

    public Reclamation(int idr, String titre, String description, String etat,String image, Date dater, int id_user, int id_typer) {
        this.idr = idr;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.dater = dater;
        this.id_user = id_user;
        this.id_typer = id_typer;
        this.etat=etat;
    }

    public Reclamation() {
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDater() {
        return dater;
    }

    public void setDater(Date dater) {
        this.dater = dater;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_typer() {
        return id_typer;
    }

    public void setId_typer(int id_typer) {
        this.id_typer = id_typer;
    }
    
}
