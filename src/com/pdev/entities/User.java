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
public class User {
    int id ;
    String email , nomUser , password;

    public User() {
    }

    public User(int id, String email, String nomUser, String password) {
        this.id = id;
        this.email = email;
        this.nomUser = nomUser;
        this.password = password;
    }

    public User(String email, String nomUser, String password) {
        this.email = email;
        this.nomUser = nomUser;
        this.password = password;
    }

    public User(String nomUser, String password) {
        this.nomUser = nomUser;
        this.password = password;
    }

    public User(int id, String email, String nomUser) {
        this.id = id;
        this.email = email;
        this.nomUser = nomUser;
    }

 

 
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
