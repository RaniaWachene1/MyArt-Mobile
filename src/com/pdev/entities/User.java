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
    int id ,telUser;
    String email , nomUser , password,role,prenomUser,img;

    public User() {
    }
///

    public User(int id, String email, String nomUser, String prenomUser, int telUser, String img) {
        this.id = id;
        this.email = email;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.telUser = telUser;
        this.img = img;
    }

    public User(String email, String nomUser, String prenomUser, int telUser, String img) {
        this.email = email;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.telUser = telUser;
        this.img = img;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public int getTelUser() {
        return telUser;
    }

    public void setTelUser(int telUser) {
        this.telUser = telUser;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
///
    
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

    public User(String email, String nomUser, String password, String role) {
        this.email = email;
        this.nomUser = nomUser;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
