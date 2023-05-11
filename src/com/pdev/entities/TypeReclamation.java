/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.entities;

/**
 *
 * @author ASUS
 */
public class TypeReclamation {
    private int idtr;
    private String nom;
    private String description;

    public TypeReclamation() {
    }

    public TypeReclamation(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public TypeReclamation(int idtr, String nom, String description) {
        this.idtr = idtr;
        this.nom = nom;
        this.description = description;
    }

    public int getIdtr() {
        return idtr;
    }

    public void setIdtr(int idtr) {
        this.idtr = idtr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TypeReclamation{" + "idtr=" + idtr + ", nom=" + nom + ", description=" + description + '}'+"\n";
    }
}
