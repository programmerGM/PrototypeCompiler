package com.unesc.compiler.util;

import com.google.gson.Gson;
import com.unesc.compiler.object.Lexico;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe para fazer a requisição ao servidor.
 *
 * @author Mauricio Gereroso
 * @since 16/09/2017
 */
public class RequestServer {

    private final String REQUEST_URL = "http://localhost:3000/";

    public Lexico compiler(final String code) {
        Lexico lexico = null;
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(REQUEST_URL).openConnection();

            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("teste parametro");
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + REQUEST_URL);
            System.out.println("Post parameters : " + "teste parametro");
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            lexico = new Gson().fromJson(response.toString(), Lexico.class);
        } catch (MalformedURLException ex) {
            System.out.println("Erro na requisição");
        } catch (IOException ex) {
            System.out.println("erro na requisição 2");
        }
        System.out.println("completou");
        return lexico;
    }

}
