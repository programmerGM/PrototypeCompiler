package com.unesc.compiler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Classe para manipular arquivos.
 *
 * @author Mauricio Generoso.
 * @since 05/09/2017
 */
public class CompilerFile {

    /**
     * Método para salvar um arquivo.
     *
     * @param file - Arquivo a ser salvo.
     * @param text - Texto para salvar no arquivo.
     * @return File - Próprio arquivo.
     */
    public File save(File file, String[] text) {
        if (file != null) {
            try (BufferedWriter fileWriter
                    = new BufferedWriter(new FileWriter(file))) {
                for (String string : text) {
                    fileWriter.write(string);
                    fileWriter.newLine();
                }
            } catch (IOException ex) {
                Util.showAlertAndWait(Alert.AlertType.ERROR, "Erro",
                        "Erro de arquivo", "Ocorreu um erro ao salvar o arquivo");
                return null;
            }
        }
        return file;
    }

    /**
     * Método para salvar um arquivo em um caminha específico.
     *
     * @param stage - Stage principal.
     * @param text - Texto a ser salvo no arquivo.
     * @return File - Retorna o arquivo salvo.
     */
    public File saveAs(Stage stage, String[] text) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Cll file", "*.cll"),
                new ExtensionFilter("Text file", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);
        return save(file, text);
    }

    /**
     * Método para retornar o arquivo a ser aberto.
     *
     * @param stage - Stage principal.
     * @return File - arquivo.
     */
    public File openGetFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo");
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

    /**
     * Método para abrir um novo arquivo.
     *
     * @param file - Arquivo a ser aberto.
     * @return String - Texto do arquivo.
     */
    public String open(File file) {
        String name = file.getName();
        if (name.endsWith(".cll") || name.endsWith(".txt")) {
            StringBuilder text = new StringBuilder();
            String line;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((line = bufferedReader.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                return text.toString();
            } catch (FileNotFoundException ex) {
                Util.showAlertAndWait(Alert.AlertType.ERROR, "Erro",
                        "Erro de arquivo", "Arquivo não encontrado");
            } catch (IOException ex) {
                Util.showAlertAndWait(Alert.AlertType.ERROR, "Erro",
                        "Erro de arquivo", "Erro ao ler arquivo");
            }
        } else {
            Util.showAlertAndWait(Alert.AlertType.INFORMATION, "Aviso",
                    "Arquivo inválido",
                    "O arquivo deve ser do tipo .cll ou .txt");
        }
        return null;
    }

}
