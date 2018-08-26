/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import espol.edu.ec.TDA.ArbolHuffman;
import espol.edu.ec.TDA.TreePrinter;
import espol.edu.ec.TDA.Util;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Map;
import javafx.scene.control.Alert;


/**
 *
 * @author MiguelPS
 */
public class Controller_compress {

    Stage stageCompress;

    @FXML
    Label labelErrorMsg;
    @FXML
    Label labelErrorMsg2;
    @FXML
    CheckBox checkRename;
    @FXML
    TextField textRename;
    @FXML
    TextField textPath;

    private File file;
    private String pathArchivo;

    /**
    * abre una ventana para elegir el archivo de texto para comprimir
    * Solo soporta ".txt"
    */
    @FXML
    void chooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto ", "*.txt"));
        file = fc.showOpenDialog(stageCompress);
        if (file != null) {
            textPath.setText(file.getAbsolutePath());
            pathArchivo = file.getAbsolutePath();
        }

    }

    /**
    * Ejecuta el proceso de compresión, genera el arbol de huffman y escribe los nuevos archivos
    * Solo soporta ".txt"
    */
    @FXML
    void compress() {
        
        //verifica si se ha elegido un archivo
        // si no, muestra un mensaje
        if (textPath.getText().length() <= 1) {
            labelErrorMsg.setVisible(true);
            return;
        } else {
            labelErrorMsg.setVisible(false);
        }

        String textoOriginal= Util.leerTexto(file.getAbsolutePath());
        
        ArbolHuffman arbol = new ArbolHuffman();

        arbol.calcularArbol(Util.calcularFrecuencias(textoOriginal));

        
        
// descomenten esta parte si quieren visualizar el arbol de huffman 
//        TreePrinter.printNode(arbol.getRaiz());

        
        if (checkRename.isSelected()) {
            if (textRename.getText().length() > 0) {
                pathArchivo = pathArchivo.substring(0, pathArchivo.length() - file.getName().length()) + textRename.getText() + ".txt";
                labelErrorMsg2.setVisible(false);
            } else {
                labelErrorMsg2.setVisible(true);
                return;
            }
        }

        Map<String, String> codigos = arbol.calcularCodigos();
        String textoCodificado = arbol.codificar(textoOriginal, codigos);
        Util.guardarTexto(pathArchivo, textoCodificado, codigos);

        AlertM msg = new AlertM(Alert.AlertType.NONE, stageCompress, "Compresión completa.");
        msg.init("Seguir comprimiendo", "Cerrar compresor");
    }

    @FXML
    void onSelectSaveWithOtherName() {
        if (checkRename.isSelected()) {
            textRename.setVisible(true);
            textRename.setDisable(false);

        } else {
            textRename.setDisable(true);
            textRename.setVisible(false);
        }

    }

    @FXML
    void close() {
        stageCompress.close();
    }

    public void setStage(Stage stage) {
        this.stageCompress = stage;
    }

}
