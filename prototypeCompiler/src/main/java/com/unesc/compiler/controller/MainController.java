package com.unesc.compiler.controller;

import com.unesc.compiler.main.GlobalStage;
import com.unesc.compiler.object.Errors;
import com.unesc.compiler.object.Lexico;
import com.unesc.compiler.object.ResponseLexico;
import com.unesc.compiler.util.CompilerFile;
import com.unesc.compiler.util.RequestServer;
import com.unesc.compiler.util.Util;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controle da janela principal.
 *
 * @author Mauricio Generoso
 * @since 04/09/2017
 * @since 16/09/2017
 */
public class MainController implements Initializable {

    private final String URL_DOCUMENTATION_GITHUB = "https://github.com/programmerGM/prototypeCompiler";

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
    private MenuItem miCompiler;
    @FXML
    private MenuItem miDocumentation;
    @FXML
    private MenuItem miAbout;
    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Lexico> tvAnalysis;
    @FXML
    private TableColumn<Lexico, String> columnLexicoLine;
    @FXML
    private TableColumn<Lexico, String> columnLexicoToken;
    @FXML
    private TableColumn<Lexico, String> columnLexicoCode;
    @FXML
    private Tab tabErrors;
    @FXML
    private TableView<Errors> tvErrors;
    @FXML
    private TableColumn<Errors, String> columnErrorLine;
    @FXML
    private TableColumn<Errors, String> columnErrorMessage;

    // Outros Objetos
    private File file;
    private String text;

    private final ObservableList<Lexico> olLexico = FXCollections.observableArrayList();
    private final ObservableList<Errors> olErrors = FXCollections.observableArrayList();

    /**
     * Método de inicialização da classe controller.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Controle dos arquivos arquivos aberto - Não mexer aqui*/
        this.file = null;
        this.text = "";
        //Fim do controle de arquivos abertos

        /*Configura a tabela do lexico*/
        this.columnLexicoLine.setCellValueFactory(
                new PropertyValueFactory<Lexico, String>("line"));
        this.columnLexicoToken.setCellValueFactory(
                new PropertyValueFactory<Lexico, String>("token"));
        this.columnLexicoCode.setCellValueFactory(
                new PropertyValueFactory<Lexico, String>("code"));
        this.tvAnalysis.setItems(olLexico);

        /*Configura a tabela de erros*/
        this.columnErrorLine.setCellValueFactory(
                new PropertyValueFactory<Errors, String>("line"));
        this.columnErrorMessage.setCellValueFactory(
                new PropertyValueFactory<Errors, String>("message"));
        this.tvErrors.setItems(olErrors);
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

    /**
     * Método para savar um arquivo.
     */
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
    private void actionMiCompiler() {
        ResponseLexico responseLexico = new RequestServer().compiler(textArea.getText());

        if (responseLexico == null) { // ocorreu erro na requisição
            return;
        }

        if (!responseLexico.getMessageError().equals("")) { // Retornou erro no código
            tabErrors.setStyle("-fx-background-color: #d16060;");
            tabErrors.getStyleClass().add("error-collor");
            olErrors.clear();
            olLexico.clear();
            olErrors.add(
                    new Errors(String.valueOf(responseLexico.getLine()[0]),
                            responseLexico.getMessageError()));

        } else {
            //Remove erros
            tabErrors.setStyle("-fx-background-color: #window;");
            olErrors.clear();
            olLexico.clear();

            System.out.println("linha: " + responseLexico.getLine()[0]);
            System.out.println("codigo: " + responseLexico.getCode()[0]);
            
            for (int i = 0; i < responseLexico.getCode().length; i++) {
                olLexico.add(
                        new Lexico(String.valueOf(responseLexico.getLine()[i]),
                                responseLexico.getToken()[i],
                                String.valueOf(responseLexico.getCode()[i])));
            }

        }
    }

    /**
     * Método para a documentação.
     */
    @FXML
    private void actionMiDocumentation() {
        try {
            Desktop.getDesktop().browse(new URI(URL_DOCUMENTATION_GITHUB));
        } catch (IOException | URISyntaxException ex) {
            Util.showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao abrir o navegador", "Erro ao abrir o navegador "
                    + "padrão do Sistema operacial.");
        }
    }

    /**
     * Ação do menuItem sobre.
     */
    @FXML
    private void actionMiAbout() {
        new Util().callAbout();
    }

}
