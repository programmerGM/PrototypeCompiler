package com.unesc.compiler.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unesc.compiler.object.ResponseLexico;
import java.lang.reflect.Type;
import java.util.HashSet;
import javafx.scene.control.Alert;
import org.javalite.http.Http;
import org.javalite.http.Post;

/**
 * Classe para fazer a requisição ao servidor.
 *
 * @author Mauricio Gereroso
 * @since 16/09/2017
 * @since 18/09/2017
 * @since 23/09/2017
 */
public class RequestServer {

    private final String REQUEST_URL = "http://localhost:80/validar";
    private final String ACCEPT = "application/json";
    private final String CONTENT_TYPE = "application/json";
    private final String PARAM_TEXTAREA = "textarea";
    private final String TYPE_CLIENT = "typeclient";
    private final String TYPE_CLIENT_CODE = "1";

    /**
     * Método para realizar a requisição ao servidor para fazer as validações no
     * código.
     *
     * @param code - Código.
     * @return HashSet - Hash com os objetos.
     */
    public HashSet<ResponseLexico> compiler(final String code) {
        HashSet<ResponseLexico> listResponse = null;
        try {
            Post post = Http.post(REQUEST_URL).header("Accept", ACCEPT)
                    .header("Content-Type", CONTENT_TYPE)
                    .params(TYPE_CLIENT, TYPE_CLIENT_CODE, PARAM_TEXTAREA, code);
            if (post.responseCode() == 200) {
                String response = post.text();
                Type type = new TypeToken<HashSet<ResponseLexico>>() {
                }.getType();
                listResponse = new Gson().fromJson(response, type);
                listResponse.forEach(l -> {
                    if (l.getCode() != 44) {
                        l.setLine(l.getLine() + 1);
                    }
                });
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            error();
        }
        return listResponse;
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
