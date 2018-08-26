/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.gui.Controller_main;
import espol.edu.ec.gui.AnchorPaneM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MiguelPS
 */
public class Main extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/espol/edu/ec/gui/view_main.fxml"));
        
        AnchorPaneM root_main = new AnchorPaneM(primaryStage, loader.load());
        Controller_main controller = loader.getController();
        controller.setStage(primaryStage);
        
        primaryStage.setScene(new Scene(root_main));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
