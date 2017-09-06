package com.unesc.compiler.controller;

import com.unesc.compiler.main.GlobalStage;
import com.unesc.compiler.util.CompilerFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 * Controle da janela principal.
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
    
    @FXML
    private TextArea textArea;

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
        new CompilerFile().saveFile(GlobalStage.getStage());
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
