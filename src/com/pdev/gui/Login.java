package com.pdev.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class Login extends Form {

    public static Form loginForm;

    public Login() {
        super("Connexion", new BoxLayout(BoxLayout.Y_AXIS));
        loginForm = this;
        addGUIs();
    }

    private void addGUIs() {


  


        Button backendBtn = new Button("Back end");
        backendBtn.addActionListener(l -> new com.pdev.gui.back.AccueilBack().show());

        this.add(backendBtn);
    }

}
