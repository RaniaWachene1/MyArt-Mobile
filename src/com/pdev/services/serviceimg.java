/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.User;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class serviceimg {
     public ArrayList<User> velos;

    public static serviceimg instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private serviceimg() {
        req = new ConnectionRequest();
    }

    public static serviceimg getInstance() {
        if (instance == null) {
            instance = new serviceimg();
        }
        return instance;
    }
///////////////////////////////////////////////////////
//    public boolean addTask(Velo t) {
//
//        String name = t.getName();
//        int status =  t.getStatus();
//        
//        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
//        String url = Statics.BASE_URL + "create/" + status + "/" + name;
//                                       //create?status=0&name=houssem
//                                       //modified
//        req.setUrl(url);
//        req.setPost(false);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

    public ArrayList<User> parseTasks(String jsonText) {
        try {
            velos = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User t = new User();
                /////////////////////////////////////////////////
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                
                
                if (obj.get("telUser") == null) {
                    t.setTelUser(00000000);
                } else {
                    t.setTelUser(((int) Float.parseFloat(obj.get("telUser").toString())));
                }
                
                
                
                
                
                if (obj.get("nomUser") == null) {
                    t.setNomUser("null");
                } else {
                    t.setNomUser(obj.get("nomUser").toString());
                }
                if (obj.get("prenomUser") == null) {
                    t.setPrenomUser("null");
                } else {
                    t.setPrenomUser(obj.get("prenomUser").toString());
                }
                if (obj.get("img") == null) {
                    t.setImg("null");
                } else {
                    t.setImg(obj.get("img").toString());
                }
                 if (obj.get("email") == null) {
                    t.setEmail("null");
                } else {
                    t.setEmail(obj.get("email").toString());
                }
                velos.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return velos;
    }

    public ArrayList<User> getAllTasks() {
        String url = Statics.BASE_URL + "getUser1";
        req.setUrl(url);
        System.out.println(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }
    
}
