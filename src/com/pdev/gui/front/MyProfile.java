/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.gui.front;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.pdev.entities.User;
import com.pdev.services.GererUserService;
import com.pdev.services.serviceimg;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class MyProfile extends Form{
    private ArrayList<User> velos = serviceimg.getInstance().getAllTasks();
    public MyProfile(Form prevForm){
        //Form previous, int s
       
        setTitle("My Profile");
        setLayout(BoxLayout.y());
       
        for (User velo : velos) {
           if(velo.getId()==27) {
            add(new Label(" "));
           
            // Create a container to hold the image and details
            Container veloContainer = new Container(new BorderLayout());
           
            // Create a container to hold the image
            Container imageContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String imageUrl = velo.getImg();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String fullImageUrl = "http://127.0.0.1:8000/images/" + imageUrl;
                try {
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(350, 550), true);
                    URLImage urlImage = URLImage.createToStorage(placeholder, "rimage_" + imageUrl, fullImageUrl, URLImage.RESIZE_SCALE);
                    Label photoEvent = new Label(urlImage);
                    imageContainer.add(photoEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            veloContainer.add(BorderLayout.WEST, imageContainer);
           
            // Create a container to hold the details
            Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nomuser = new Label("Nom : " + velo.getNomUser());
            Label pnomuser = new Label("Pnom : " + velo.getPrenomUser());
            Label tel = new Label("Ntel: " + velo.getTelUser());
            
            Button bb=new Button("modifier");
            add(bb);
            detailsContainer.add(nomuser);
            detailsContainer.add(pnomuser);
            detailsContainer.add(tel);
            veloContainer.add(BorderLayout.CENTER, detailsContainer);
//            veloContainer.add(bb);
            // Add the velo container to the form
            this.add(veloContainer);
            bb.addActionListener(e->{
                Dialog.show("Success","   ",new Command("OK"));
                //do you
                //(String dateDebut, String dateFin, int nbr, float prixr, String idVelo, String idStation)
            //ServiceReservationVelo.getInstance().addTask(r);
                //Dialog.show("Success","reservation ajoutee",new Command("OK"));
               // previous.show();
            });
        }        }

       
        add(new Label("\n"));
        add(new Label("\n"));
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prevForm.showBack());
    }
    
    
}
