package com.unesc.compiler.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe utilitária.
 *
 * @author Maurício Generoso
 * @since 05/07/2017
 */
public class Util {

    /**
     * Método para retornar o ícone do programa.
     *
     * @return Icone
     */
    public Image getIcon() {
        return new Image(getClass().getResourceAsStream("/icon.png"));
    }

    /**
     * Método para demonstrar alerta em tela e aguardar
     *
     * @param type Tipo de alerta
     * @param title Título
     * @param headerText Cabçalho da mensagem
     * @param text Corpo da mensagem
     */
    public static void showAlertAndWait(AlertType type, String title, String headerText, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(text);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Util().getIcon());
        alert.showAndWait();
    }

}
