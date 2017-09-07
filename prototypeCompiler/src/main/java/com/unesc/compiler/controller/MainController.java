package com.unesc.compiler.controller;

import com.unesc.compiler.main.GlobalStage;
import com.unesc.compiler.util.CompilerFile;
import java.io.File;
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
    private File file;
    private String text;

    /**
     * Método de inicialização da classe controller.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = null;
        text = "";
    }

    /**
     * Método de evento para iniciar um novo arquivo.
     */
    @FXML
    private void actionMiNewFile() {
        file = null;
        textArea.setText("");
    }

    /**
     * Método de evento para abrir um novo arquivo.
     */
    @FXML
    private void actionMiOpenFile() {
        File temp = new CompilerFile().openGetFile(GlobalStage.getStage());
        if (temp != null) {
            String text = new CompilerFile().open(temp);
            if (text != null) {
                textArea.setText("" + text);
                file = temp;
            }
            text = null;
        }
        temp = null;
    }

    /**
     * Método para salvar o arquivo corrente.
     */
    @FXML
    private void actionMiSalveFile() {
        if (file != null) {
            new CompilerFile().save(file, textArea.getText().split("\n"));
        } else {
            actionMiSaveAsFile();
        }
    }

    @FXML
    private void actionMiSaveAsFile() {
        File temp = new CompilerFile().saveAs(GlobalStage.getStage(), textArea.getText().split("\n"));
        if (temp != null) {
            file = temp;
        }
        temp = null;
    }

    /**
     * Ação para o item de menu para sair.
     */
    @FXML
    private void actionMiExit() {
        System.exit(0);
    }

    /**
     * Ação para o menu de compilar.
     */
    @FXML
    private void actionMCompiler() {
        System.out.println("Em construção.");
    }

    @FXML
    private void actionMiDocumentation() {
        System.out.println("Em construção.");
    }

    @FXML
    private void actionMiAbout() {
        System.out.println("");
    }
    
}
