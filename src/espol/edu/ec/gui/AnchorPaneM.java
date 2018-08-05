/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * AnchoPane, mueve la ventana al hacer clic dentro y arrastrar con el mouse
 * @author MiguelPS
 */
public class AnchorPaneM extends AnchorPane{

    double x,y;
    
    /**
    * @param stage stage que contiene el root. Se usa para obtener la posicion X,Y 
    * @param root anchorPane con toda la UI
    * @return caracter hexadecimal (obtenido de HEX_CARACTER con el valor decimal correspondiente)
    */
    public AnchorPaneM(Stage stage, AnchorPane root) {
        
        super(root);
        
        this.setOnMousePressed((event) -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });
        
        this.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });
        
    }
 
}
