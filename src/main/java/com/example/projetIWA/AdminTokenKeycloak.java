package com.example.projetIWA;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class AdminTokenKeycloak {

    public static String getToken() throws IOException {
        String res = "";

        URL url = new URL("http://localhost:8180/auth/realms/master/protocol/openid-connect/token");
        //Open a Connection
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        //Set the Request Method
        con.setRequestMethod("POST");
        //Set the Request Content-Type Header Parameter
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; utf-8");
        //Set Response Format Type
        con.setRequestProperty("Accept", "application/json");
        //Ensure the Connection Will Be Used to Send Content
        con.setDoOutput(true);
        //Create the Request Body
        /*
        String jsonInputString = "{\"username\":\"lauren\",\"password\":\"lauren\",\"grant_type\":\"password\",\"client_id\":\"admin_cli\"}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            res = input.toString();
        }
        */
        Map<String,String> arguments = new HashMap<>();
        arguments.put("username", "root");
        arguments.put("password", "sjh76HSn!"); // This is a fake password obviously
        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        con.setFixedLengthStreamingMode(length);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.connect();
        try(OutputStream os = con.getOutputStream()) {
            os.write(out);
        }


         return con.getInputStream().toString();

    }

}
