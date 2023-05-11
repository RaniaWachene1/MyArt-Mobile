/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.front;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class AccueilFront extends Form{
    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilFront() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }
    private void addGUIs(){
        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.isScrollableY();
        menuContainer.addAll(
                //userContainer,
                
                reclamationButton()
        );

        this.add(menuContainer);
    }
    private Button reclamationButton() {
        Button button = new Button("    Reclamations");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.front.reclamation.DisplayAll(this).show());
        return button;
    }
    
}
