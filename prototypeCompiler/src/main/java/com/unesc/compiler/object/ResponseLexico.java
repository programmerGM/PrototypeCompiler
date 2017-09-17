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

    public ResponseLexico() {
        messageError = null;
    }

    public int[] getCode() {
        return code;
    }

    public void setCode(int[] code) {
        this.code = code;
    }

    public String[] getToken() {
        return token;
    }

    public void setToken(String[] token) {
        this.token = token;
    }

    public int[] getLine() {
        return line;
    }

    public void setLine(int[] line) {
        this.line = line;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    @Override
    public String toString() {
        return "ResponseLexico{" + "code=" + code + ", token=" + token + ", line=" + line + ", messageError=" + messageError + '}';
    }

}
