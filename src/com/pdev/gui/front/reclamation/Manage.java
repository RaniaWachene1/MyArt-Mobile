/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.front.reclamation;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.pdev.entities.Reclamation;
import com.pdev.services.ReclamationService;
import java.io.IOException;
import java.util.Date;

public class Manage extends Form {

    Reclamation currentReclamation;

    Label titleLabel, descriptionLabel, imageLabel, dateLabel, userIdLabel, typeIdLabel,etatLabel;
    TextField titleTF, descriptionTF, imageTF, userIdTF, typeIdTF;
    Picker dateP;

    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(com.pdev.gui.back.reclamation.DisplayAll.currentReclamation == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReclamation = com.pdev.gui.front.reclamation.DisplayAll.currentReclamation;
        if (currentReclamation == null) {
            addGUIs();
            addActions();
        } else {
            modifier();
            modifieraction();
        }

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        titleLabel = new Label("Titre : ");
        titleLabel.setUIID("labelDefault");
        titleTF = new TextField();
        titleTF.setHint("Tapez le titre");

        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez la description");

        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        imageTF = new TextField();
        imageTF.setHint("Tapez l'URL de l'image");

        dateLabel = new Label("Date : ");
        dateLabel.setUIID("labelDefault");
        dateP = new Picker();
        dateP.setType(Display.PICKER_TYPE_DATE);

        userIdLabel = new Label("User ID : ");
        userIdLabel.setUIID("labelDefault");
        userIdTF = new TextField();
        userIdTF.setHint("Tapez l'ID utilisateur");

        typeIdLabel = new Label("Type ID : ");
        typeIdLabel.setUIID("labelDefault");
        typeIdTF = new TextField();
        typeIdTF.setHint("Tapez l'ID type");

        manageButton = new Button("Ajouter ");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(titleLabel, titleTF, descriptionLabel, descriptionTF, imageLabel, imageTF, dateLabel, dateP, userIdLabel, userIdTF, typeIdLabel, typeIdTF, manageButton);

        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                
                    int responseCode = ReclamationService.getInstance().add(
                            new Reclamation(
                                    titleTF.getText(),
                                    descriptionTF.getText(),
                                    imageTF.getText(),
                                    "Unprocessed",
                                    dateP.getDate(),
                                    Integer.parseInt(userIdTF.getText()),
                                    Integer.parseInt(typeIdTF.getText())
                            )
                    );
                
                if (responseCode == 200) {
                    Dialog.show("Succés", "Reclamation ajouté avec succès", new Command("Ok"));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de Reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void modifier() {
        titleLabel = new Label("Titre : ");
        titleLabel.setUIID("labelDefault");
        titleTF = new TextField();
        titleTF.setHint("Tapez le titre");
        titleTF.setText(currentReclamation.getTitre());

        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez la description");
        descriptionTF.setText(currentReclamation.getDescription());

        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        imageTF = new TextField();
        imageTF.setHint("Tapez l'URL de l'image");
        imageTF.setText(currentReclamation.getImage());

        dateLabel = new Label("Date : ");
        dateLabel.setUIID("labelDefault");
        dateP = new Picker();
        dateP.setType(Display.PICKER_TYPE_DATE);
        dateP.setDate(currentReclamation.getDater());

        userIdLabel = new Label("User ID : ");
        userIdLabel.setUIID("labelDefault");
        userIdTF = new TextField();
        userIdTF.setHint("Tapez l'ID utilisateur");
        userIdTF.setText(String.valueOf(currentReclamation.getId_user()));

        typeIdLabel = new Label("Type ID : ");
        typeIdLabel.setUIID("labelDefault");
        typeIdTF = new TextField();
        typeIdTF.setHint("Tapez l'ID type");
        typeIdTF.setText(String.valueOf(currentReclamation.getId_typer()));

        manageButton = new Button("Modifier ");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(titleLabel, titleTF, descriptionLabel, descriptionTF, imageLabel, imageTF, dateLabel, dateP, userIdLabel, userIdTF, typeIdLabel, typeIdTF, manageButton);

        this.addAll(container);
    }

    private void modifieraction() {
        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                
                    int responseCode = ReclamationService.getInstance().edit(
                            new Reclamation(
                                    currentReclamation.getIdr(),
                                    titleTF.getText(),
                                    descriptionTF.getText(),
                                    "Unprocessed",
                                    imageTF.getText(),
                                    dateP.getDate(),
                                    Integer.parseInt(userIdTF.getText()),
                                    Integer.parseInt(typeIdTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Reclamation modifié avec succès", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                
            }
        });
    }

    private void showBackAndRefresh() {
        ((com.pdev.gui.front.reclamation.DisplayAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {
        
        return true;
    }
}