package com.unesc.compiler.object;

import java.io.Serializable;

/**
 * Classe com os dados do analisados léxico.
 *
 * @author Mauricio Generoso.
 * @since 16/09/2017
 */
public class ResponseLexico implements Serializable {

    private int code;
    private String token;
    private int line;
    private String messageError;

    /**
     * Construtor.
     */
    public ResponseLexico() {
        messageError = null;
    }

    /**
     * Retorna o código.
     *
     * @return int - Array com os códigos.
     */
    public int getCode() {
        return code;
    }

    /**
     * Insere um código.
     *
     * @param code - Código a ser inserido.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Retorna o token.
     *
     * @return - String - Token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Insere o token.
     *
     * @param token String - Token a ser inserido.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Retorna a linha.
     *
     * @return int - Linha.
     */
    public int getLine() {
        return line;
    }

    /**
     * Insere a linha.
     *
     * @param line String - Linha a ser inserida.
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Retorna a mensagem de erro.
     *
     * @return String - Mensagem de erro.
     */
    public String getMessageError() {
        return messageError;
    }

    /**
     * Insere uma mensagem de erro.
     *
     * @param messageError - Mensagem a ser inserida.
     */
    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

}
