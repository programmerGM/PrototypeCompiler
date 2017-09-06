package com.unesc.compiler.controller;

import com.unesc.compiler.object.Code;
import com.unesc.compiler.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controle da janela principal
 *
 * @author Mauricio Generoso
 * @since 04/09/2017
 */
public class MainController implements Initializable {

    @FXML
    private MenuItem miNewFile;
    @FXML
    private MenuItem miOpenFile;
    @FXML
    private MenuItem miSalveFile;
    @FXML
    private MenuItem miSaveAsFile;
    @FXML
    private MenuItem miExit;
    @FXML
    private Menu mCompiler;
    @FXML
    private MenuItem miDocumentation;
    @FXML
    private MenuItem miAbout;

    // Objetos
    private Code code;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void actionMiNewFile() {
        System.out.println("teste");
    }

    @FXML
    private void actionMiOpenFile() {
        System.out.println("teste");
    }

    @FXML
    private void actionMiSalveFile() {
        System.out.println("teste");
    }

    @FXML
    private void actionMiSaveAsFile() {
        new Util().showAlertAndWait(Alert.AlertType.INFORMATION, "teste", "teste", "teste");
    }

    @FXML
    private void actionMCompiler() {
        System.out.println("compilou");
    }

    @FXML
    private void actionMiExit() {
        System.out.println("teste");
        System.exit(-1);
    }

    @FXML
    private void actionMiDocumentation() {
        System.out.println("teste");
    }

    @FXML
    private void actionMiAbout() {
        System.out.println("teste");
    }

}
