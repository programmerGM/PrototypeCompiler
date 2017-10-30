package com.unesc.compiler.main;

import com.unesc.compiler.util.Util;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Classe da janela principal.
 *
 * @author Mauricio Generoso
 * @since 04/09/2017
 */
public class Main extends Application {

    /**
     * Método de inicialiação.
     *
     * @param primaryStage - Janela.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        GlobalStage.getInstance(primaryStage);
        initLayout();
    }

    /**
     * Método de inicialização do layout.
     */
    private void initLayout() {
        try {
            //System.out.println(getClass().getResource("/fxml/Main.fxml"));
            GlobalStage.loadNewStage((Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")));
            GlobalStage.getStage().getIcons().add(new Util().getIcon());
        } catch (IOException ex) {
            Util.showAlertAndWait(Alert.AlertType.ERROR, "ERRO",
                    "Erro na abertura da janela", "Ocorreu um erro na abertura"
                    + "da janela.\nO programa será finalizado.");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
