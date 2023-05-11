/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pdev.entities.Articles;
import com.pdev.services.ArticlesService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author aminh
 */
public class AfficherArticles  extends Form {
        Resources theme1 = UIManager.initFirstTheme("/theme");
     Form previous;

    public static Articles currentArticles= null;
    Button addBtn;


    public AfficherArticles(Form previous) {
        super("Articles", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Articles> listArticles = ArticlesService.getInstance().getAll();


        if (listArticles.size() > 0) {
            for (Articles articles : listArticles) {
                Component model = makeArticlesModel(articles);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentArticles = null;
            new GererArtciles(this).show();
        });

    }

    Label sujetLabel, sujetLabe2, sujetLabe3,sujetLabe4,sujetLabe5;


    private Container makeModelWithoutButtons(Articles articles) {
        Container articlesModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        articlesModel.setUIID("containerRounded");


      

        sujetLabel = new Label("titre_article : " + articles.getTitre_article());
        sujetLabel.setUIID("labelDefault");
        
       sujetLabe2 = new Label("nom_artiste : " + articles.getNom_artiste());
        sujetLabe2.setUIID("labelDefault");
        
         sujetLabe3 = new Label("prix_article : " + articles.getPrix_article());
        sujetLabe3.setUIID("labelDefault");
        
         sujetLabe4 = new Label("desc_article : " + articles.getDesc_article());
        sujetLabe4.setUIID("labelDefault");
        
         sujetLabe5 = new Label("quantite_article : " + articles.getQuantite_article());
        sujetLabe5.setUIID("labelDefault");
     



        articlesModel.addAll(

             sujetLabel,sujetLabe2 , sujetLabe3, sujetLabe4 , sujetLabe5
        );

        return articlesModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeArticlesModel(Articles articles) {

        Container articlesModel = makeModelWithoutButtons(articles);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentArticles = articles;
            new GererArtciles(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce article ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ArticlesService.getInstance().delete(articles.getId());

                if (responseCode == 200) {
                    currentArticles = null;
                    dlg.dispose();
                    articlesModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du article. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        articlesModel.add(btnsContainer);

        return articlesModel;
    }
    
}
