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

    static String[] hexCharacters = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    static int decValueOfHex(String hex) {
        for (int i = 0; i < hexCharacters.length; i++) {
            if (hex.equals(hexCharacters[i])) {
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
        StringBuilder temp = new StringBuilder();
       
        int i;
        for (i = 0; i < binario.length(); i++) {

            temp.append(binario.charAt(i));
            if ((i + 1) % 4 == 0) {
                hexadecimal.append(binToHex(temp));
                temp = new StringBuilder();
            }
        }
        hexadecimal.append(temp);

        return hexadecimal.toString();
    }

    private static String binToHex(StringBuilder bin) {
        StringBuilder hex = new StringBuilder();

        int dec = 0;
        int exp=0;
        int index=bin.length()-1;
        while (index>=0) {
            int b = bin.charAt(index--) == '0' ? 0 : 1;
            dec += (int) b * Math.pow(2, exp++);
        }
        
        return hexCharacters[dec];
    }

    public static String hexadecimalBinario(String hexadecimal) {
        StringBuilder binario = new StringBuilder();
        StringBuilder temp;

        for (int i = 0; i < hexadecimal.length(); i++) {
            temp = new StringBuilder();
            int decValue = decValueOfHex(String.valueOf(hexadecimal.charAt(i)));

            while (decValue > 0) {
                temp.append(decValue % 2);
                decValue = decValue / 2;
            }
            while (temp.length() < 4) {
                temp.append("0");
            }
            binario.append(temp.reverse().toString());

        }
        return binario.toString();
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
