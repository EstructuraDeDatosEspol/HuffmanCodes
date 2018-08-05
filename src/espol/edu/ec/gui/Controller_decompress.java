/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import espol.edu.ec.TDA.ArbolHuffman;
import espol.edu.ec.TDA.Util;
import java.io.File;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML TextField textPath;
    @FXML TextField textCodesPath;
    
    private File file;
    private File fileCodes;
    private String pathArchivo;
    private String pathArchivoCodes;
    private FileChooser fc;
   
    
    @FXML void chooseFile(){
        fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto ", "*.huff"));
        file = fc.showOpenDialog(stageDecompress);
        if (file != null) {
            textPath.setDisable(false);
            textPath.setText(file.getAbsolutePath());
            pathArchivo = file.getAbsolutePath();
        }
    }
    
    @FXML void chooseFileCodes(){
        fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto ", "*.codes"));
        fileCodes = fc.showOpenDialog(stageDecompress);
        if (fileCodes != null) {
            textCodesPath.setDisable(false);
            textCodesPath.setText(fileCodes.getAbsolutePath());
            pathArchivoCodes = fileCodes.getAbsolutePath();
        }
    }
    
    @FXML void decompress(){
        if(file==null || fileCodes==null){
            labelErrorMsg.setVisible(true);
            return;
        }

        String textoCodificado;
        String textoSinCodificar;    
        
        HashMap<String, Integer> frecuencias;
        HashMap<String, String> codigos;
        
        textoCodificado = Util.leerTexto(file.getAbsolutePath());
        codigos = Util.leerMapa(pathArchivoCodes);
        textoSinCodificar = ArbolHuffman.decodificar(textoCodificado, codigos);
        
        Util.guardarDecodificado(pathArchivo, textoSinCodificar);
        
        AlertM msg = new AlertM(Alert.AlertType.NONE, stageDecompress, "Descompresión completa.");
        msg.init("Seguir descomprimiendo", "Cerrar descompresor");
        
    }
    
    @FXML void close(){
        stageDecompress.close();
    }

    public void setStage(Stage stage) {
        this.stageDecompress = stage;
    }
    
}
