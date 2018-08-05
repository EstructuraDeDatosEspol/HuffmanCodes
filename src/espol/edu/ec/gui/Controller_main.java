/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MiguelPS
 */
public class Controller_main {

    Stage stage_main;
    Stage stageCompress;
    Stage stageDecompress;

    @FXML void optionCompress() throws IOException{
        if(stageDecompress!=null)
            stageDecompress.close();
        
        if(stageCompress!=null)
            stageCompress.close();
            
        stageCompress = new Stage();
        stageCompress.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_compress.fxml"));
        AnchorPaneM root = new AnchorPaneM(stageCompress, loader.load());
        Controller_compress controller = loader.getController();
        controller.setStage(stageCompress);
        
        stageCompress.setScene(new Scene(root));
        stageCompress.setX(stage_main.getX()*0.6);
        stageCompress.setY(stage_main.getY()*1.2);
        stageCompress.show();
    }
    
    
    @FXML void optionDecompress() throws IOException{
        
         if(stageDecompress!=null)
            stageDecompress.close();
        
        if(stageCompress!=null)
            stageCompress.close();
        
        stageDecompress = new Stage();
        stageDecompress.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view_decompress.fxml"));
        AnchorPaneM root = new AnchorPaneM(stageDecompress, loader.load());
        Controller_decompress controller = loader.getController();
        controller.setStage(stageDecompress);
        
        stageDecompress.setScene(new Scene(root));
        stageDecompress.setX(stage_main.getX()*1.2);
        stageDecompress.setY(stage_main.getY()*1.2);
        stageDecompress.show();
    }
    
    @FXML void close(){
        stage_main.close();
    }
        
    public void setStage(Stage stage) {
        this.stage_main = stage;
    }
    
}
