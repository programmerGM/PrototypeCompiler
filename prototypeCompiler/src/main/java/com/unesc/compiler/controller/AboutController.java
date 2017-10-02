package com.unesc.compiler.controller;

import com.unesc.compiler.main.GlobalStage;
import com.unesc.compiler.util.Util;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Mauricio Generoso
 * @since 07/09/2017
 * @since 01/10/2017
 */
public class AboutController implements Initializable {

    private final String URL_PROJECT_DESKTOP = "https://github.com/programmerGM/prototypeCompiler";
    private final String URL_PROJECT_SERVER = "https://github.com/programmerGM/prototypeCompilerServer";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //image.setImage(new Image("/../../java/com/unesc/compiler/util/images/unesc.png"));
    }

    @FXML
    private void onActionClient() {
        try {
            Desktop.getDesktop().browse(new URI(URL_PROJECT_DESKTOP));
        } catch (IOException | URISyntaxException ex) {
            Util.showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao abrir o navegador", "Erro ao abrir o navegador "
                    + "padrão do Sistema operacial.");
        }
    }

    @FXML
    private void onActionServer() {
        try {
            Desktop.getDesktop().browse(new URI(URL_PROJECT_SERVER));
        } catch (IOException | URISyntaxException ex) {
            Util.showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao abrir o navegador", "Erro ao abrir o navegador "
                    + "padrão do Sistema operacial.");
        }
    }

    @FXML
    private void onActionReturn() {
        new Util().closeAbout();
        GlobalStage.getStage().show();
    }
}
