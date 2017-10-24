package com.unesc.compiler.object;

import java.io.Serializable;

/**
 * Classe com os dados do analisados léxico.
 *
 * @author Mauricio Generoso.
 * @since 24/10/2017
 */
public class ResponseSintatico implements Serializable {

    private int code;
    private boolean add;

    /**
     * Construtor.
     */
    public ResponseSintatico() {

    }

    public ResponseSintatico(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

}
