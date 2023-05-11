/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.entities;

public class datas implements Comparable<datas>{
    private int nbre;
    private int id;
    private String Nom; 
    private float rate; 

    public datas(int nbre,int id, String Nom ,float r) {
        this.nbre = nbre;
        this.Nom = Nom;
        this.id = id;
        this.rate=r;
    }

    public int getNbre() {
        return nbre;
    }

    public String getNom() {
        return Nom;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public int compareTo(datas o) {
        return (o.getNbre()-this.getNbre());
    }
    
}