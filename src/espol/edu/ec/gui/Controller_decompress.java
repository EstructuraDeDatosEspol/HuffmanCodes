/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author MiguelPS
 */
public class Controller_decompress {
    
    Stage stageDecompress;
    
    @FXML Label labelErrorMsg;
    @FXML CheckBox checkRename;
    @FXML TextField textRename;
    @FXML TextField textPath;
    
    private File file;
   
    
    @FXML void chooseFile(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto ", "*.txt"));
        file = fc.showOpenDialog(stageDecompress);
        if(file!= null)
            textPath.setText(file.getAbsolutePath());
    }
    @FXML void decompress(){
        if(textPath.getText().length()<=1){
            labelErrorMsg.setText("Ingrese la ruta del archivo.");
            labelErrorMsg.setTextFill(Color.valueOf("#eb6777"));
            labelErrorMsg.setVisible(true);
        }

        
    }
    
    @FXML void actionSelect(){
        if(checkRename.isSelected()){
            textRename.setVisible(true);
            textRename.setDisable(false);
            
        }else{
            textRename.setDisable(true);
            textRename.setVisible(false);
        }
        
    }
    
    @FXML void close(){
        stageDecompress.close();
    }

    public void setStage(Stage stage) {
        this.stageDecompress = stage;
    }
    
}
