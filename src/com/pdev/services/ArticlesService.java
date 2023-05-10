/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.services;


import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.pdev.entities.Articles;
import com.pdev.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aminh
 */
public class ArticlesService {
    
    
       public static ArticlesService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Articles> listArticles;


    private ArticlesService() {
        cr = new ConnectionRequest();
    }

    public static ArticlesService getInstance() {
        if (instance == null) {
            instance = new ArticlesService();
        }
        return instance;
    }

    public ArrayList<Articles> getAll() {
        listArticles = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "getArticles");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listArticles = getList();
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

        return listArticles;
    }

    private ArrayList<Articles> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Articles articles = new Articles(
                        (int) Float.parseFloat(obj.get("idArticle").toString()),
                (int) Float.parseFloat(obj.get("quantiteArticle").toString()),
                        (String) obj.get("titreArticle"),
                        (String) obj.get("nomArtiste"),
                        (String) obj.get("descArticle"),
                          (String) obj.get("photoArticle"),
                        (float) Float.parseFloat(obj.get("prixArticle").toString())
                       
                       
                        
                     

                );

                listArticles.add(articles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listArticles;
    }
    
     public int add(Articles articles, String email) {
        return manage(articles, false, email);
    }

    public int edit(Articles articles) {
        return manage(articles, true, "");
    }

    public int manage(Articles articles, boolean isEdit, String email) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "editArticlesJSON");
            cr.addArgument("idArticle", String.valueOf(articles.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "addArticlesJSON");
            cr.addArgument("email", email);
        }

        
        
 
        cr.addArgument("titreArticle", articles.getTitre_article());
            cr.addArgument("nomArtiste", articles.getNom_artiste());
             cr.addArgument("prixArticle", Float.toString(articles.getPrix_article()));

             cr.addArgument("descArticle", articles.getDesc_article());
              cr.addArgument("quantiteArticle", Float.toString(articles.getQuantite_article()));
                 cr.addArgument("photoArticle", articles.getPhoto_article());
              
              
                    
    
     


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


       public int delete(int Id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "jsonardelete");
        cr.setHttpMethod("DELETE");
        cr.addArgument("idArticle", String.valueOf(Id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
