package com.unesc.compiler.object;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe para as mensagens de erros.
 *
 * @author Mauricio Generoso
 * @since 16/09/2017
 */
public class Errors {

    private final SimpleStringProperty line;
    private final SimpleStringProperty message;

    public Errors(String line, String message) {
        this.line = new SimpleStringProperty(line);
        this.message = new SimpleStringProperty(message);
    }

    public String getLine() {
        return line.get();
    }

    public String getMessage() {
        return message.get();
    }

}
