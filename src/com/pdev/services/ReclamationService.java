/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.Reclamation;
import com.pdev.utils.FilterBadWord;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ReclamationService {
    public static ReclamationService instance=null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Reclamation> listReclamations;
    private ReclamationService() {
        cr = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

    public ArrayList<Reclamation> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "reclam/allreclam");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    System.out.println("****************************\n"+"done");
                    System.out.println("Raw JSON Response: " + new String(cr.getResponseData()));

                    listReclamations = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReclamations;
    }

    private ArrayList<Reclamation> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            System.out.println("****************************\n"+parsedJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            System.out.println("****************************\n"+list);

            for (Map<String, Object> obj : list) {
                System.out.println(obj+"\n");
                Reclamation reclamation = new Reclamation(
                        (int) Float.parseFloat(obj.get("idr").toString()),
                        (String) obj.get("titre"),
                        (String) obj.get("description"),
                        (String) obj.get("etat"),
                        (String) obj.get("image"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((String) obj.get("dater")),1,1
                        //(int) Float.parseFloat(obj.get("id_user").toString()),
                       // (int) Float.parseFloat(obj.get("id_typer").toString())
                );

                listReclamations.add(reclamation);
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return listReclamations;
    }

    public int add(Reclamation reclamation)  {
        return manage(reclamation, false);
    }

    public int edit(Reclamation reclamation)  {
        return manage(reclamation, true);
    }

    public int manage(Reclamation reclamation, boolean isEdit) {

        try {
            
            cr = new ConnectionRequest();
            
            cr.setHttpMethod("POST");
            if (isEdit) {
                cr.setUrl(Statics.BASE_URL + "reclam/updateReclam");
                cr.addArgument("idr", String.valueOf(reclamation.getIdr()));
            } else {
                cr.setUrl(Statics.BASE_URL + "reclam/addReclam");
            }
            cr.addArgument("dater", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDater()));
            cr.addArgument("image", reclamation.getImage());
            cr.addArgument("titre", reclamation.getTitre());
            cr.addArgument("idtyper", String.valueOf(reclamation.getId_typer()));
            cr.addArgument("idUser", String.valueOf(reclamation.getId_user()));
            cr.addArgument("description", FilterBadWord.filter(reclamation.getDescription()));
            cr.addArgument("etat", reclamation.getEtat());
            
            cr.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultCode = cr.getResponseCode();
                    cr.removeResponseListener(this);
                }
            });
            try {
                cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
                NetworkManager.getInstance().addToQueueAndWait(cr);
            } catch (Exception ignored) {
                
            }
            
        } catch (IOException ex) {

        }
        return resultCode;
    }

    public int delete(int reclamationId) {
        
         ConnectionRequest cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "reclam/deleteReclam/" + reclamationId);
        //cr.setHttpMethod(f);
        cr.setPost(false);

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (cr.getResponseCode() == 200) {
                    System.out.println("Reclam deleted successfully: " + new String(cr.getResponseData()));
                } else if (cr.getResponseCode() == 404) {
                    System.out.println("Reclam not found");
                } else {
                    System.out.println("Error: " + cr.getResponseCode());
                }
                cr.removeResponseListener(this);
            }
        });
        
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 200;
    }
}
