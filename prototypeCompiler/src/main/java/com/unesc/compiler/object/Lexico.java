package com.unesc.compiler.object;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe com os dados do analisados léxico.
 *
 * @author Mauricio Generoso.
 * @since 16/09/2017
 * @since 18/09/2017
 */
public class Lexico {

    private final SimpleStringProperty line;
    private final SimpleStringProperty token;
    private final SimpleStringProperty code;

    /**
     * Construtor.
     *
     * @param line - Linha.
     * @param token - Token.
     * @param code - Código.
     */
    public Lexico(String line, String token, String code) {
        this.line = new SimpleStringProperty(line);
        this.token = new SimpleStringProperty(token);
        this.code = new SimpleStringProperty(code);
    }

    /**
     * Retorna array a linha.
     *
     * @return String - Linha.
     */
    public String getLine() {
        return line.get();
    }

    /**
     * Retorna o token.
     *
     * @return String - Token.
     */
    public String getToken() {
        return token.get();
    }

    /**
     * Retorna o código.
     *
     * @return String - Código.
     */
    public String getCode() {
        return code.get();
    }

}
