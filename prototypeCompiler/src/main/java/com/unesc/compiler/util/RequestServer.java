package com.unesc.compiler.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unesc.compiler.object.ResponseLexico;
import com.unesc.compiler.object.ResponseSintatico;
import java.lang.reflect.Type;
import java.util.List;
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

    private final String REQUEST_URL_LEXICO = "http://localhost:80/validar";
    private final String REQUEST_URL_SINTATICO = "http://localhost:80/sintatico";
    private final String ACCEPT = "application/json";
    private final String CONTENT_TYPE = "application/json";
    private final String PARAM_TEXTAREA = "textarea";
    private final String PARAM_TOKENS = "tokens";
    private final String TYPE_CLIENT = "typeclient";
    private final String TYPE_CLIENT_CODE = "1";

    /**
     * Método para realizar a requisição ao servidor para fazer as validações no
     * código.
     *
     * @param code - Código.
     * @return HashSet - Hash com os objetos.
     */
    public List<ResponseLexico> compilerLexico(final String code) {
        List<ResponseLexico> listLexico = null;
        try {
            Post post = Http.post(REQUEST_URL_LEXICO).header("Accept", ACCEPT)
                    .header("Content-Type", CONTENT_TYPE)
                    .params(TYPE_CLIENT, TYPE_CLIENT_CODE, PARAM_TEXTAREA, code);
            if (post.responseCode() == 200) {
                Type type = new TypeToken<List<ResponseLexico>>() {
                }.getType();
                listLexico = new Gson().fromJson(post.text(), type);
                listLexico.forEach(l -> {
                    if (l.getCode() != 44) {
                        l.setLine(l.getLine() + 1);
                    }
                });
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            error();
        }
        return listLexico;
    }

    public List<ResponseSintatico> compilerSintatico(List<ResponseLexico> listlexico) {
        List<ResponseSintatico> listSintatico = null;
        try {
            Type type = new TypeToken<List<ResponseSintatico>>() {
            }.getType();
            System.out.println(new Gson().toJson(listlexico, type));
            Post post = Http.post(REQUEST_URL_SINTATICO).header("Accept", ACCEPT)
                    .header("Content-Type", CONTENT_TYPE)
                    .params(TYPE_CLIENT, TYPE_CLIENT_CODE, PARAM_TOKENS, new Gson().toJson(listlexico, type));
            if (post.responseCode() == 200) {
                listSintatico = new Gson().fromJson(post.text(), type);
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            error();
        }
        return listSintatico;
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
