package com.unesc.compiler.util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Classe para manipular arquivos.
 *
 * @author Mauricio Generoso.
 * @since 05/09/2017
 */
public class CompilerFile {

    public void saveFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo");
        
        fileChooser.showSaveDialog(stage);
        
    }

}
