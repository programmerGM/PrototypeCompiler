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

    /**
     * Construtor.
     *
     * @param line - Linha.
     * @param message - Mensagem do erro.
     */
    public Errors(String line, String message) {
        this.line = new SimpleStringProperty(line);
        this.message = new SimpleStringProperty(message);
    }

    /**
     * Retorna a linha.
     *
     * @return String - Linha.
     */
    public String getLine() {
        return line.get();
    }

    /**
     * Retorna a mensagem de erro.
     *
     * @return String - mensagem de erro.
     */
    public String getMessage() {
        return message.get();
    }

}
