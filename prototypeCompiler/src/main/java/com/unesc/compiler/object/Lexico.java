package com.unesc.compiler.object;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe com os dados do analisados léxico.
 *
 * @author Mauricio Generoso.
 * @since 16/09/2017
 */
public class Lexico {

    private final SimpleStringProperty line;
    private final SimpleStringProperty token;
    private final SimpleStringProperty code;

    public Lexico(String line, String token, String code) {
        this.line = new SimpleStringProperty(line);
        this.token = new SimpleStringProperty(token);
        this.code = new SimpleStringProperty(code);
    }

    public String getLine() {
        return line.get();
    }

    public String getToken() {
        return token.get();
    }

    public String getCode() {
        return code.get();
    }

}
