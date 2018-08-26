/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.stage.Stage;

/**
 * muestra un alert cuando la operacion de compresion o descompresion se hayan realizado
 * @author MiguelPS
 */
public class AlertM extends Alert {
    
    ButtonType continueWith;
    ButtonType close;
    String text1;
    String text2;
    Stage stage;

    public AlertM(AlertType alertType, Stage stage, String msg) {
        super(alertType);
        initStyle(StageStyle.UNDECORATED);
        setContentText(msg);
        this.stage=stage;
    }
    
    public void init(String text1, String text2){
        continueWith = new ButtonType(text1);
        close = new ButtonType(text2, ButtonBar.ButtonData.CANCEL_CLOSE);
        getButtonTypes().setAll(continueWith, close);
        Optional<ButtonType> result = showAndWait();
        if (result.isPresent()) {
            if (result.get() == continueWith) {
                close();
            } else {
                close();
                stage.close();
            }
        }

    };

}
