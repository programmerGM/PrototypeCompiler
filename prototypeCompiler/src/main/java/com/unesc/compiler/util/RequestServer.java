package com.unesc.compiler.util;

import com.google.gson.Gson;
import com.unesc.compiler.object.ResponseLexico;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.control.Alert;

/**
 * Classe para fazer a requisição ao servidor.
 *
 * @author Mauricio Gereroso
 * @since 16/09/2017
 */
public class RequestServer {

    private final String REQUEST_URL = "http://localhost:3000/";

    /**
     * Faz requisição enviando o código o objeto o retorno.
     *
     * @param code - Código.
     * @return ResponseLexico - Objeto com os dados do retorno.
     */
    public ResponseLexico compiler(final String code) {
        ResponseLexico responseLexico = null;
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

            responseLexico = new Gson().fromJson(response.toString(), ResponseLexico.class);
        } catch (MalformedURLException ex) {
            error();
        } catch (IOException ex) {
            error();
        }
        return responseLexico;
    }

    /**
     * Para quando ocorrer erro mostrar uma janela de diálogo.
     */
    private void error() {
        Util.showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                "Erro de comunicação", "Não foi possível compilar o código. "
                + "Ocorreu um erro ao comunicar-se com o Servidor.");
    }

}
