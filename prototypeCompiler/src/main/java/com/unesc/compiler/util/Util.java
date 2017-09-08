package com.unesc.compiler.util;

import com.unesc.compiler.main.GlobalStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
     * Método para chamar a janela Sobre.
     */
    public void callAbout() {
        try {
            Stage about = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/About.fxml")));
            about.setScene(scene);
            about.setTitle("Sobre");
            //about.initModality(Modality.WINDOW_MODAL); Não funfa
            GlobalStage.getStage().hide();
            about.show();
        } catch (IOException ex) {
            showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro na abertura da janela", "Ocorreu um erro na abertura"
                    + "da janela.");
        }
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
