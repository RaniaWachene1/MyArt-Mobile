/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.utils;

/**
 *
 * @author ASUS
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.JSONParser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class FilterBadWord {

    public static String filter(String msg) {
        String apiURL = "https://www.purgomalum.com/service/json?text=" + msg;
        
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(apiURL);
        request.setPost(false);
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        Map<String, Object> result;
        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            return (String) result.get("result");
        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean checkBadWords(String msg) {
        String apiURL = "https://www.purgomalum.com/service/containsprofanity?text=" + msg;
        
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(apiURL);
        request.setPost(false);
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        return new String(request.getResponseData()).equals("true");
    }
}
