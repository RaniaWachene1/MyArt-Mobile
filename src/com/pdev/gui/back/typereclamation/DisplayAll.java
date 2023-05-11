/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back.typereclamation;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.TypeReclamation;
import com.pdev.services.TypeReclamationService;
import java.util.ArrayList;
import static com.pdev.gui.back.reclamation.DisplayAll.currentReclamation;

/**
 *
 * @author ASUS
 */
public class DisplayAll extends Form{
    public static TypeReclamation currentTypeReclamation = null;
    Button addBtn;
    Label  titleLabel,  descriptionLabel;
    Button editBtn, deleteBtn;

    Container btnsContainer;

    public DisplayAll(Form previous) {
        super("Type Reclamations", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter ");
        addBtn.setUIID("buttonWhiteCenter");

        this.add(addBtn);

        ArrayList<TypeReclamation> listEntities = TypeReclamationService.getInstance().getAll(); 
        if (listEntities.size() > 0) {
            for (TypeReclamation entity : listEntities) {
                System.out.println("****");
                this.add(makeEntityModel(entity));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentTypeReclamation = null;
            new Manage(this).show();
        });
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private Component makeEntityModel(TypeReclamation entity) {
        Container entityModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        entityModel.setUIID("containerRounded");

        titleLabel = new Label("Nom : " + entity.getNom());
        titleLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + entity.getDescription());
        descriptionLabel.setUIID("labelDefault");
        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonMain");
        editBtn.addActionListener(action -> {
            currentTypeReclamation = entity;
            new com.pdev.gui.back.typereclamation.Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce reclamation ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = TypeReclamationService.getInstance().delete(entity.getIdtr());

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
                titleLabel, descriptionLabel,
                btnsContainer
        );

        return entityModel;
    }
}
