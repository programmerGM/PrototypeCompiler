package com.unesc.compiler.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mauricio Generoso
 * @since 07/09/2017
 */
public class AboutController implements Initializable {

    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //image.setImage(new Image("/../../java/com/unesc/compiler/util/images/unesc.png"));
    }

}