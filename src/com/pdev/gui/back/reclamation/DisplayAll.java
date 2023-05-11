/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back.reclamation;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class DisplayAll extends Form {

    public static Reclamation currentReclamation = null; // Rename 'NewEntity' to the actual name of your new entity class
    Button addBtn;
    Label titleLabel, descriptionLabel, imageLabel, dateLabel;
    TextField search;
    Button searchButton;

    public DisplayAll(Form previous) {
        super("New Entities", new BoxLayout(BoxLayout.Y_AXIS));
        // Create button
    Button statsButton = new Button("View Statistics");
    statsButton.setUIID("LoginButton");
    // Add action listener to navigate to ReclamationStatForm
    statsButton.addActionListener(e -> new ReclamationStat(this).show());
    // Add button to form
    this.add(statsButton);

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        getTitleArea().setUIID("Container");
        setUIID("MultiLine1");
        search = new TextField("", "Search..."); // Search field
        search.setUIID("TextFieldBlack");
        searchButton = new Button("Search");
        searchButton.setUIID("LoginButton");
        Container searchContainer = BorderLayout.center(search).
                add(BorderLayout.EAST, searchButton);
        searchContainer.setUIID("PaddedContainer");
        this.add(searchContainer);
        searchButton.addActionListener(e -> performSearch());
        ArrayList<Reclamation> listEntities = ReclamationService.getInstance().getAll(); // Rename 'NewEntity' and 'NewEntityService' to the actual names of your new entity and service classes
        if (listEntities.size() > 0) {
            for (Reclamation entity : listEntities) {
                this.add(makeEntityModel(entity));
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        this.refreshTheme();
    }

    private Component makeEntityModel(Reclamation entity) {
        Container entityModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        entityModel.setUIID("containerRounded");

        titleLabel = new Label("Titre : " + entity.getTitre());
        titleLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + entity.getDescription());
        descriptionLabel.setUIID("labelDefault");

        imageLabel = new Label("Image : " + entity.getImage());
        imageLabel.setUIID("labelDefault");

        dateLabel = new Label("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(entity.getDater()));
        dateLabel.setUIID("labelDefault");

        entityModel.addAll(
                titleLabel, descriptionLabel, imageLabel, dateLabel
        );

        return entityModel;
    }

    private void performSearch() {
        String searchTerm = search.getText();
        if (searchTerm.equals("")) {
            refresh();
        } else {
            
            this.removeAll();
            ArrayList<Reclamation> listEntities = ReclamationService.getInstance().search(searchTerm);
            if (listEntities.size() > 0) {
                for (Reclamation entity : listEntities) {
                    this.add(makeEntityModel(entity));
                }
            } else {
                this.add(new Label("Aucune donnée"));
            }
            
            this.refreshTheme();
        }
    }
}
