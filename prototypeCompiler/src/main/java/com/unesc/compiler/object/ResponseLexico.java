package com.unesc.compiler.object;

import java.io.Serializable;

/**
 * Classe com os dados do analisados léxico.
 *
 * @author Mauricio Generoso.
 * @since 16/09/2017
 */
public class ResponseLexico implements Serializable {

    private int[] code;
    private String[] token;
    private int[] line;
    private String messageError;

    /**
     * Construtor.
     */
    public ResponseLexico() {
        messageError = null;
    }

    /**
     * Retorna um array com os códigos.
     *
     * @return int[] - Array com os códigos.
     */
    public int[] getCode() {
        return code;
    }

    /**
     * Insere um array com os códigos.
     *
     * @param code - Array a ser inserido.
     */
    public void setCode(int[] code) {
        this.code = code;
    }

    /**
     * Retorna um array com os tokens.
     *
     * @return - String[] - Array com os tokens.
     */
    public String[] getToken() {
        return token;
    }

    /**
     * Insere um array com os tokens.
     *
     * @param token String[] - Array a ser inserido.
     */
    public void setToken(String[] token) {
        this.token = token;
    }

    /**
     * Retorna um array com as linhas.
     *
     * @return int[] - Array cmo as linhas.
     */
    public int[] getLine() {
        return line;
    }

    /**
     * Insere um array com as linhas.
     *
     * @param line String[] - Array a ser inserido.
     */
    public void setLine(int[] line) {
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

    /**
     * toString().
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "ResponseLexico{" + "code=" + code + ", token=" + token + ", line=" + line + ", messageError=" + messageError + '}';
    }

}
