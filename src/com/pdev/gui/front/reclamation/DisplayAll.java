/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.front.reclamation;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;
import java.util.ArrayList;

public class DisplayAll extends Form {

    public static Reclamation currentReclamation = null;
    Button addBtn;
    Label idLabel, titleLabel, descriptionLabel, imageLabel, dateLabel, userIdLabel, typeIdLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public DisplayAll(Form previous) {
        super("Reclamations", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        getTitleArea().setUIID("Container");
        setUIID("MultiLine1");
        addBtn = new Button("Ajouter ");
        addBtn.setUIID("buttonWhiteCenter");

        this.add(addBtn);

        ArrayList<Reclamation> listEntities = ReclamationService.getInstance().getAll();
        if (listEntities.size() > 0) {
            for (Reclamation entity : listEntities) {
                System.out.println("****");
                this.add(makeEntityModel(entity));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentReclamation = null;
            new Manage(this).show();
        });
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private Component makeEntityModel(Reclamation entity) {
        Container entityModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        entityModel.setUIID("containerRounded");

        idLabel = new Label("ID: " + entity.getIdr());
        idLabel.setUIID("labelDefault");

        titleLabel = new Label("Titre : " + entity.getTitre());
        titleLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + entity.getDescription());
        descriptionLabel.setUIID("labelDefault");

        imageLabel = new Label("Image : " + entity.getImage());
        imageLabel.setUIID("labelDefault");

        dateLabel = new Label("Date : " + entity.getDater());
        dateLabel.setUIID("labelDefault");

        userIdLabel = new Label("User ID : " + entity.getId_user());
        userIdLabel.setUIID("labelDefault");

        typeIdLabel = new Label("Type ID : " + entity.getId_typer());
        typeIdLabel.setUIID("labelDefault");

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonMain");
        editBtn.addActionListener(action -> {
            currentReclamation = entity;
            new com.pdev.gui.front.reclamation.Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer cette reclamation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ReclamationService.getInstance().delete(entity.getIdr());
                if (responseCode == 200) {
                    System.out.println("delete done");
                    currentReclamation = null;
                    dlg.dispose();
                    entityModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.CENTER, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        entityModel.addAll(
                idLabel, titleLabel, descriptionLabel, imageLabel, dateLabel, userIdLabel, typeIdLabel,
                btnsContainer
        );

        return entityModel;
    }
}