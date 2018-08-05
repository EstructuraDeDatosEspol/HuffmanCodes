/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author MiguelPS
 */
public class Util {

    final public static String SEPARATOR = "|";

    final static String[] HEX_CARACTER = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    static int decValueOfHex(String hex) {
        for (int i = 0; i < HEX_CARACTER.length; i++) {
            if (hex.equals(HEX_CARACTER[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String leerTexto(String pathArchivo) {
        StringBuilder text = new StringBuilder();

        Scanner s;

        try {
            s = new Scanner(new File(pathArchivo));
            while (s.hasNextLine()) {
                text.append(s.nextLine());
            }
            s.close();

        } catch (FileNotFoundException e) {

            System.out.println("Ha ocurrido un error al leer el archivo. " + e.getMessage());
        }
        return text.toString();
    }

    public static HashMap<String, Integer> calcularFrecuencias(String texto) {

        HashMap<String, Integer> frecuencias = new HashMap<>();

        for (int i = 0; i < texto.length(); i++) {
            String c = String.valueOf(texto.charAt(i));
            if (frecuencias.containsKey(c)) {
                frecuencias.put(c, frecuencias.get(c) + 1);
            } else {
                frecuencias.put(c, 1);
            }
        }
        return frecuencias;
    }

    public static String binarioHexadecimal(String binario) {

        StringBuilder hexadecimal = new StringBuilder();
        StringBuilder tempBin = new StringBuilder();
        
        for (int i = 0; i < binario.length(); i++) {

            tempBin.append(binario.charAt(i));
            if ((i + 1) % 4 == 0) {
                hexadecimal.append(binToHex(tempBin));
                tempBin = new StringBuilder();
            }
        }
        
        //agrega "-" al final del codigo hex por cada cero que se suprime en el binario 
        StringBuilder hex = new StringBuilder();
        int j=0;
        while(j < tempBin.length() && tempBin.charAt(j)=='0') {
                hex.append("-");
        }
        hex.append(binToHex(tempBin));
        hexadecimal.append(hex.reverse());

        return hexadecimal.toString();
    }
    
    /**
    * método auxiliar para convertir un binario de 4 o menos bits a hexadecimal
    * 
    *@param bin representa el binario de un valor decimal ( base 10)
    *@return caracter hexadecimal (obtenido de HEX_CARACTER con el valor decimal correspondiente)
    */
    private static String binToHex(StringBuilder bin) {
        
        int dec = 0;
        int exp = 0;
        int index=bin.length()-1;
        while (index>=0) {
            int b = bin.charAt(index--) == '0' ? 0 : 1;
            dec += (int) b * Math.pow(2, exp++);
        }
        
        return HEX_CARACTER[dec];
    }

    public static String hexadecimalBinario(String hexadecimal) {
        StringBuilder binario = new StringBuilder();
        StringBuilder temp;
        
        // "extra" cuenta la cantidad de ceros suprimidos 
        int extra = 0;
        int fin=hexadecimal.length()-1;
        while(hexadecimal.charAt(fin--)=='-')
            extra++;
        
        // itera hasta el penúltimo caracter hexadecimal
        // por lo cual se agregan ceros al principio del binario hasta completar los 4 bits
        fin=hexadecimal.length()-extra-1;
        for (int i = 0; i < fin; i++) {
            temp = new StringBuilder();
            int decValue = decValueOfHex(String.valueOf(hexadecimal.charAt(i)));

            temp.append(decToBin(decValue));
            
            while (temp.length() < 4) {
                temp.append("0");
            }
            binario.append(temp.reverse());
        }
        
        // calcula el binario correspondiente al ultimo caracter hexadecimal
        // tomando en cuenta la cantidad ceros suprimidos, lo cual es determinado por "extra"
        temp=new StringBuilder(decToBin(decValueOfHex(String.valueOf(hexadecimal.charAt(fin)))));
        while(extra-- > 0){
            temp.append("0");
        }
        binario.append(temp.reverse());
        
        return binario.toString();
    }
    
    /**
    * método auxiliar para convertir un numero decimal a binario
    * @param dec representa el valor decimal
    * @return String con el correspondiente binario 
    */
    private static String decToBin(int dec){
        StringBuilder temp = new StringBuilder();
        while (dec > 0) {
                temp.append(dec % 2);
                dec = dec / 2;
            }
        return temp.toString();
    }
    

    public static void guardarTexto(String path, String texto, HashMap<String, String> codigos) {

        StringBuilder line = new StringBuilder();
        File file;

        try {
            file = new File(path.substring(0,path.length()-4)+"_codificado.txt");
            PrintWriter generatedFile = new PrintWriter(file);
            generatedFile.print(texto);
            generatedFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un problema al generar el archivo. " + e.getMessage());
        }

        try {
            file = new File(path.substring(0, path.length()-4) + "_codificado_codes.txt");
            PrintWriter codesFile = new PrintWriter(file);

            for (Map.Entry<String, String> entry : codigos.entrySet()) {
                line.append(entry.getKey()).append(SEPARATOR).append(entry.getValue()).append("\n");
            }
            codesFile.print(line);
            codesFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un problema al generar el archivo de codigos. " + e.getMessage());
        }
    }

    public static HashMap<String, String> leerMapa(String nombreArchivo) {

        HashMap<String, String> mapa = new HashMap<>();
        Scanner s;

        try {
            s = new Scanner(new File(nombreArchivo));
            while (s.hasNextLine()) {
                String data[] = s.nextLine().split("\\|");
                mapa.put(data[1], data[0]);  // key = binario, value = caracter
            }
            s.close();

        } catch (FileNotFoundException e) {

            System.out.println("Ha ocurrido un error al leer el archivo. " + e.getMessage());
        }

        return mapa;
    }
    
        /**
    * método para generar un archivo ".txt" con el texto codificado se toma 
    * la ruta y nombre del texto original (no codificado) agregandole el identificador "_decodificado.txt"
    * @param path es la ruta + nombre del nuevo archivo
    * @param texto texto codificado
    * @return String con el correspondiente binario 
    */
    public static void guardarDecodificado(String path, String texto){
        StringBuilder line = new StringBuilder();
        File file;

        try {
            file = new File(path.substring(0,path.length()-4)+"_decodificado.txt");
            PrintWriter generatedFile = new PrintWriter(file);
            generatedFile.print(texto);
            generatedFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un problema al generar el archivo. " + e.getMessage());
        }
    }
}