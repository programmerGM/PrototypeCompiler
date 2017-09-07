package com.unesc.compiler.main;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Stage Global da aplicação.
 *
 * @author Maurício Generoso
 * @since 05/09/2017
 */
public class GlobalStage {

    private static GlobalStage globalStage = null;
    private static Stage stage;

    /**
     * Construtor.
     *
     * @param stage - Stage principal.
     */
    private GlobalStage(Stage stage) {
        GlobalStage.stage = stage;
    }

    /**
     * Retorna instância da Stage.
     *
     * @param stage - Stage a ser instânciada.
     * @return GlobalStage - Instância global da Stage.
     */
    public static GlobalStage getInstance(Stage stage) {
        if (globalStage == null) {
            globalStage = new GlobalStage(stage);
        }
        return globalStage;
    }

    /**
     * Retorna a stage.
     *
     * @return Stage - Stage global.
     */
    public static Stage getStage() {
        return GlobalStage.stage;
    }

    /**
     * Método para carregar uma nova Stage.
     *
     * @param fxmlLoad FXML com a Stage.
     */
    public static void loadNewStage(Parent fxmlLoad) {
        GlobalStage.stage.hide();
        GlobalStage.stage = new Stage();
        Parent root = fxmlLoad;
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        GlobalStage.stage.setScene(scene);
        GlobalStage.getStage().setTitle("Prototipo de compilador");
        GlobalStage.stage.show();
    }
}
