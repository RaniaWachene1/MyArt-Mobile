/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.entities.Articles;
import com.pdev.services.ArticlesService;


/**
 *
 * @author aminh
 */
public class GererArtciles extends Form {
    
   
    Resources theme1 = UIManager.initFirstTheme("/theme");
     
    Articles currentArticles;

    TextField titreTF;
    Label titreLabel;
    
       TextField nomarticleTF;
    Label nomarticleLabel;
    
       TextField prixTF;
    Label prixLabel;
    
       TextField descTF;
    Label descLabel;
    
       TextField quantityTF;
    Label quantityLabel;

   


    Button manageButton;
    
     

    Form previous;

    public GererArtciles(Form previous) {
        super(AfficherArticles.currentArticles == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentArticles = AfficherArticles.currentArticles;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        titreLabel = new Label("titre_article : ");
        titreLabel.setUIID("labelDefault");
        titreTF = new TextField();
        titreTF.setHint("Tapez le titre_article");
        
        
        nomarticleLabel = new Label("nom_artiste : ");
        nomarticleLabel.setUIID("labelDefault");
        nomarticleTF = new TextField();
        nomarticleTF.setHint("Tapez le nom_artiste");
        
        
        prixLabel = new Label("prix_article : ");
        prixLabel.setUIID("labelDefault");
        prixTF = new TextField();
        prixTF.setHint("Tapez le prix_article");
        
        
        descLabel = new Label("desc_article : ");
        descLabel.setUIID("labelDefault");
        descTF = new TextField();
        descTF.setHint("Tapez le desc_article");
        
        
        quantityLabel = new Label("quantite_article : ");
        quantityLabel.setUIID("labelDefault");
        quantityTF = new TextField();
        quantityTF.setHint("Tapez le quantite_article");








        if (currentArticles== null) {


            manageButton = new Button("Ajouter");
        } else {


                titreTF.setText(currentArticles.getTitre_article());

                       nomarticleTF.setText(currentArticles.getNom_artiste());

                         prixTF.setText(currentArticles.getPrix_article().toString());


                                     descTF.setText(currentArticles.getDesc_article());

                   quantityTF.setText(String.valueOf(currentArticles.getQuantite_article()));

        
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");
 
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
        container.setUIID("containerRounded");

        container.addAll(


                titreLabel, titreTF,
                nomarticleLabel, nomarticleTF,
                prixLabel,prixTF,
                descLabel,descTF,
                quantityLabel,quantityTF,
          
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {
        

        
        
        
        
        
        
        
   if (currentArticles == null) {
       
       
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = ArticlesService.getInstance().add(
                    new Articles(
                     
                           Integer.parseInt(quantityTF.getText()),
                            titreTF.getText(),
                            nomarticleTF.getText(),
                            descTF.getText(),
                             
                            Float.parseFloat(prixTF.getText())
                            
                            
                          
                    ),
                    ""
            );
            if (responseCode == 200) {
                //EmailSender.sendEmail(); // call the email sending method here
                Dialog.show("Succés", "Articles ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Articles. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ArticlesService.getInstance().edit(
                            new Articles(
                                    currentArticles.getId(),
                                    Integer.parseInt(quantityTF.getText()),
                                     titreTF.getText(),
                                    nomarticleTF.getText(),
                                      descTF.getText(),
                                    
                                      Float.parseFloat(prixTF.getText())
                                  
                         
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Articles modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Articles. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherArticles) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (titreTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
        
          if (nomarticleTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
          
            if (prixTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
            
              if (descTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
              
                if (quantityTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }



       

        return true;
    }
}
