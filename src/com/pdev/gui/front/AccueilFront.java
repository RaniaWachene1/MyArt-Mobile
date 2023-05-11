/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.front;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.gui.Login;
import com.pdev.gui.back.LoginUser;

/**
 *
 * @author ASUS
 */
public class AccueilFront extends Form{
    Toolbar tb=getToolbar();
    Label label;
//
    public AccueilFront() {
        
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        super.getToolbar().addCommandToLeftBar(new Command("Logout") {
            
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Clear the current session
        Display.getInstance().callSerially(() -> {
            com.codename1.io.Preferences.clearAll();
        });

        // Navigate to the login page
        Login login = new Login();
        new LoginUser(login).show();
Display.getInstance().getCurrent().removeAll();
      
    }
});
        addGUIs();
    }
    
    private void addGUIs(){
        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.isScrollableY();
        menuContainer.addAll(
                //userContainer,
                
    
                reclamationButton(),
                article()
        );
        
tb.addMaterialCommandToSideMenu("My profile", FontImage.MATERIAL_APPS , 200, e->{
    new MyProfile(this).show();
    });
        this.add(menuContainer);
    }
    private Button reclamationButton() {
        Button button = new Button("    Reclamations");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.front.reclamation.DisplayAll(this).show());
        return button;
    }
    
    
       private Button article() {
        Button button = new Button(" article");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.front.articles.AfficherArticles(this).show());
        return button;
    }
    
}
