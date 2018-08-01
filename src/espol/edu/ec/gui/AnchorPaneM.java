/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author MiguelPS
 */
public class AnchorPaneM extends AnchorPane{

    double x,y;
    
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
