/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.gui.back.typereclamation;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.TypeReclamation;
import com.pdev.services.TypeReclamationService;

public class Manage extends Form {

    TypeReclamation currentTypeReclamation;

    Label nameLabel, descriptionLabel;
    TextField nameTF, descriptionTF;

    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(DisplayAll.currentTypeReclamation == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentTypeReclamation = DisplayAll.currentTypeReclamation;
        if (currentTypeReclamation == null) {
            addGUIs();
            addActions();
        } else {
            modifier();
            modifieraction();
        }

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        getTitleArea().setUIID("Container");
        setUIID("MultiLine1");
        nameLabel = new Label("Nom : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le nom");

        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez la description");

        manageButton = new Button("Ajouter ");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(nameLabel, nameTF, descriptionLabel, descriptionTF, manageButton);

        this.addAll(container);
    }

    private void addActions() {

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = TypeReclamationService.getInstance().add(
                        new TypeReclamation(
                                nameTF.getText(),
                                descriptionTF.getText()
                        )
                );
                if (responseCode == 200) {
                    Dialog.show("Succés", "TypeReclamation ajouté avec succès", new Command("Ok"));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de TypeReclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void modifier() {
        nameLabel = new Label("Nom : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le nom");
        nameTF.setText(currentTypeReclamation.getNom());
        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez la description");
        descriptionTF.setText(currentTypeReclamation.getDescription());
        manageButton = new Button("Modifier ");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(nameLabel, nameTF, descriptionLabel, descriptionTF, manageButton);

        this.addAll(container);

    }

    private void modifieraction() {
        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = TypeReclamationService.getInstance().edit(
                        new TypeReclamation(
                                currentTypeReclamation.getIdtr(),
                                nameTF.getText(),
                                descriptionTF.getText()
                        )
                );
                if (responseCode == 200) {
                    Dialog.show("Succés", "TypeReclamation modifier avec succès", new Command("Ok"));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur de modification de TypeReclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((DisplayAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {

        if (nameTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir le nom", new Command("Ok"));
            return false;
        }

        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Veuillez saisir la description", new Command("Ok"));
            return false;
        }

        return true;
    }
}
