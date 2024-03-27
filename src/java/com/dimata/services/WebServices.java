/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author gndiw
 */
public class WebServices {
    
    public static JSONObject getAPI(String payload, String url) {
            JSONObject object = new JSONObject();
            
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpRequest = new HttpGet(url);
            HttpResponse httpResponse;
            String httpResult = "";
            try {
                httpResponse = httpClient.execute(httpRequest);
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    httpResult = EntityUtils.toString(httpResponse.getEntity());
                    object = new JSONObject(httpResult);
                }
            } catch (Exception exc) {
            }
            return object;
        }
    
    public static JSONObject postAPI(String payload, String urls) {
        JSONObject data = new JSONObject();
        try {
            System.out.println(urls);
            StringBuilder result = new StringBuilder(); 
            URL url = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
            
            try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            System.out.println("URLl = >"+urls); 
            JSONObject jsonObjectResult = new JSONObject(result.toString());
            data = jsonObjectResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data; 
    }
}
