package com.pdev.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.TypeReclamation;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeReclamationService {
    public static TypeReclamationService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<TypeReclamation> listTypeReclamations;

    private TypeReclamationService() {
        cr = new ConnectionRequest();
    }

    public static TypeReclamationService getInstance() {
        if (instance == null) {
            instance = new TypeReclamationService();
        }
        return instance;
    }

    public ArrayList<TypeReclamation> getAll() {
        listTypeReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "typereclamation/alltypereclam");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (cr.getResponseCode() == 200) {
                    System.out.println("Raw JSON Response: " + new String(cr.getResponseData()));

                    listTypeReclamations = getList();
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

        return listTypeReclamations;
    }

    private ArrayList<TypeReclamation> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            for (Map<String, Object> obj : list) {
                TypeReclamation typeReclamation = new TypeReclamation(
                        (int) Float.parseFloat(obj.get("idtr").toString()),
                        (String) obj.get("nom"),
                        (String) obj.get("description")
                );

                listTypeReclamations.add(typeReclamation);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listTypeReclamations;
    }
     public int add(TypeReclamation typereclam) {
        return manage(typereclam, false);
    }

    public int edit(TypeReclamation typereclam) {
        return manage(typereclam, true);
    }

    public int manage(TypeReclamation typereclam, boolean isEdit) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "typereclamation/updateTypeReclamation");
            cr.addArgument("idtr", String.valueOf(typereclam.getIdtr()));
            System.out.println(typereclam.getIdtr());
        } else {
            cr.setUrl(Statics.BASE_URL + "typereclamation/addTypeReclamation");
        }
        
        cr.addArgument("nom", typereclam.getNom());
        cr.addArgument("description", typereclam.getDescription());

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
        return resultCode;
    }

    public int delete(int reclamationId) {
        ConnectionRequest cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "typereclamation/deleteTypeReclamation/" + reclamationId);
        //cr.setHttpMethod(f);
        cr.setPost(false);

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (cr.getResponseCode() == 200) {
                    System.out.println("Type deleted successfully: " + new String(cr.getResponseData()));
                } else if (cr.getResponseCode() == 404) {
                    System.out.println("Type not found");
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
