package com.unesc.compiler.controller;

import com.unesc.compiler.main.GlobalStage;
import com.unesc.compiler.object.Errors;
import com.unesc.compiler.object.Lexico;
import com.unesc.compiler.object.ResponseLexico;
import com.unesc.compiler.object.ResponseSintatico;
import com.unesc.compiler.util.CompilerFile;
import com.unesc.compiler.util.RequestServer;
import com.unesc.compiler.util.Util;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @since 18/09/2017
 * @since 23/09/2017
 * @since 01/10/2017
 */
public class MainController implements Initializable {

    private final String URL_DOCUMENTATION_GITHUB = "https://programmergm.github.io/prototypeCompiler/";

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
    private TableView<ResponseSintatico> tvStack;
    @FXML
    private TableColumn<ResponseSintatico, String> columnSintaticoStack;
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

    private final ObservableList<Lexico> olLexico
            = FXCollections.observableArrayList();
    private final ObservableList<Errors> olErrors
            = FXCollections.observableArrayList();
    private final ObservableList<ResponseSintatico> olStack
            = FXCollections.observableArrayList();

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
                new PropertyValueFactory<>("line"));
        this.columnLexicoToken.setCellValueFactory(
                new PropertyValueFactory<>("token"));
        this.columnLexicoCode.setCellValueFactory(
                new PropertyValueFactory<>("code"));
        this.tvAnalysis.setItems(olLexico);

        /*Configura a tabela de erros*/
        this.columnErrorLine.setCellValueFactory(
                new PropertyValueFactory<>("line"));
        this.columnErrorMessage.setCellValueFactory(
                new PropertyValueFactory<>("message"));
        this.tvErrors.setItems(olErrors);

        /*Configura a pilha*/
        this.columnSintaticoStack.setCellValueFactory(
                new PropertyValueFactory<>("code"));
        this.tvStack.setItems(olStack);
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
        List<ResponseLexico> responseLexico = new RequestServer().compilerLexico(textArea.getText());

        if (responseLexico == null) { // ocorreu erro na requisição e o objeto retornou nulo da função
            return;
        }
        // Limpa tudo
        tabErrors.setStyle("-fx-background-color: #window;");
        olErrors.clear();
        olLexico.clear();
        olStack.clear();

        responseLexico.forEach(list -> {
            if (list.getMessageError() != null) { // Há erro
                tabErrors.setStyle("-fx-background-color: #d16060;");
                olErrors.add(new Errors(String.valueOf(list.getLine()),
                        list.getMessageError()));
            } else {
                // Adiciona tokens
                olLexico.add(
                        new Lexico(String.valueOf(list.getLine()),
                                list.getToken(),
                                String.valueOf(list.getCode())));
            }
        });

        if (olErrors.isEmpty()) {
            List<ResponseSintatico> sintatico = new RequestServer().compilerSintatico(responseLexico);

            new Thread(() -> {
                sintatico.forEach(s -> {
                    if (s.isAdd()) {
                        System.out.println("Adicionou o " + s.getCode());
                        olStack.add(0, new ResponseSintatico(s.getCode()));
                    } else {
                        if (s.getCode() != 44) {
                            System.out.println("Removeu o " + s.getCode());
                            olStack.remove(0);
                        }
                    }
                    try {
                        System.out.println("esperando");
                        Thread.sleep(1500l);
                        System.out.println("próximo");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                if (olStack.size() > 1) {
                    olStack.add(0, new ResponseSintatico(-9999999));
                }
            }).start();
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
