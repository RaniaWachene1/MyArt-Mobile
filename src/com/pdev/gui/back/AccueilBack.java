package com.pdev.gui.back;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.gui.Login;


public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilBack() {
        
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();

        
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


    }

    private void addGUIs() {
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            Login.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeUserButton(),
            // makenewbutton
                makeARticlesButton(),
                reclamationButton(),
                typereclamationButton(),
                stat()
             
        );

        this.add(menuContainer);
    }

 


    
     private Button makeARticlesButton() {
        Button button = new Button("Articles");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherArticles(this).show());
        return button;
    }
     
   
       
     
        private Button makeUserButton() {
        Button button = new Button("users");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOK);
        button.addActionListener(action -> new AfficherUser(this).show());
        return button;
    }
        private Button reclamationButton() {
        Button button = new Button("    Reclamations");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.back.reclamation.DisplayAll(this).show());
        return button;
    }
    private Button typereclamationButton() {
        Button button = new Button("    Type Reclamations");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.back.typereclamation.DisplayAll(this).show());
        return button;
    }
          
     private Button stat() {
        Button button = new Button(" stat ");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_ERROR);
        button.addActionListener(action -> new com.pdev.gui.back.stat.statForm(this).show());
        return button;
    }

}
