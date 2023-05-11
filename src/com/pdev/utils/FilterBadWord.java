/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdev.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.codename1.io.Util;

/**
 *
 * @author Skymil
 */
public class FilterBadWord {
    public static String filter(String msg) throws UnsupportedEncodingException, IOException{
        String apiURL="https://www.purgomalum.com/service/json?text="+Util.encodeUrl(msg);
        URL url=new URL(apiURL);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String result="";
        while((line=reader.readLine())!=null){
            result+=line;
        }
        reader.close();
        String jsonResponse=result;
        
        return jsonResponse.substring(jsonResponse.indexOf(":\"")+2,jsonResponse.lastIndexOf("\"}"));
    }
    public static boolean checkBadWords(String msg) throws UnsupportedEncodingException, MalformedURLException, IOException{
        String apiURL="https://www.purgomalum.com/service/containsprofanity?text="+Util.encodeUrl(msg);
        URL url=new URL(apiURL);
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        //System.out.println(reader.readLine());
        return reader.readLine().equals("true");
    }
}
