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
 * @since 05/09/2017
 * @since 01/10/2017
 */
public class Util {

    private final String URL_ICON_1 = "https://image.flaticon.com/teams/slug/freepik.jpg";
    private final String URL_ICON_2 = "https://image.flaticon.com/teams/slug/freepik.jpg";
    private final String URL_ICON_3 = "https://image.flaticon.com/teams/slug/freepik.jpg";

    private static Stage about;

    /**
     * Método para retornar o ícone do programa.
     *
     * @return Icone
     */
    public Image getIcon() {
        return new Image(URL_ICON_1);
    }

    /**
     * Método para chamar a janela Sobre.
     */
    public void callAbout() {
        try {
            about = new Stage();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/About.fxml")));
            about.setScene(scene);
            about.setTitle("Sobre");
            GlobalStage.getStage().hide();
            about.getIcons().add(new Util().getIcon());
            about.show();
        } catch (IOException ex) {
            showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro na abertura da janela", "Ocorreu um erro na abertura"
                    + "da janela.");
        }
    }

    public void closeAbout(){
        about.close();
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
