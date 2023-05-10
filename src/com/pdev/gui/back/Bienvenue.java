/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.gui.back;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;
import com.pdev.gui.Login;

/**
 *
 * @author Acer
 */
public class Bienvenue extends Form{
    Login login = new Login();
    public Bienvenue(Form prevForm){
        setUIID("i1");
        getToolbar().setUIID("ayhaja");
        setLayout(new FlowLayout(CENTER, BOTTOM));
        Button BB=new Button("Welcome");
        add(BB);
        BB.addActionListener(e->{
        new LoginUser(login).show();
        });
    }
}
