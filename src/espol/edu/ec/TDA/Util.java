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

    final static String SEPARATOR = ",";

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
                text.append(" ");
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
            String c = String.valueOf(texto.charAt(i)).toLowerCase();

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

        for (int i = 0; i < bin.length(); i++) {
            int b = bin.charAt(i) == '0' ? 0 : 1;
            dec += (int) b * Math.pow(2, i);
        }

        System.out.println(dec);
        return hexCharacters[dec];
    }

    public static String hexadecimalBinario(String hexadecimal) {
        StringBuilder binario = new StringBuilder();

        int decValue = decValueOfHex(hexadecimal);

        while (decValue > 0) {
            binario.append(decValue % 2);
            decValue = decValue / 2;
        }
        while (binario.length() < 4) {
            binario.append("0");
        }
        return binario.reverse().toString();
    }

    public static void guardarTexto(String nombreArchivo, String texto, HashMap<String, String> mapa) {

        StringBuilder line = new StringBuilder();

        try {
            PrintWriter generatedFile = new PrintWriter(nombreArchivo + ".txt");
            generatedFile.println(texto);

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un problema al generar el archivo. " + e.getMessage());
        }

        try {
            PrintWriter codesFile = new PrintWriter(nombreArchivo + "_codes.txt");

            for (Map.Entry<String, String> entry : mapa.entrySet()) {
                line.append(entry.getKey()).append(SEPARATOR).append(entry.getValue());
                codesFile.println(line);
                line = new StringBuilder();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un problema al generar el archivo de codigos. " + e.getMessage());
        }
    }

    public static HashMap<String, String> leerMapa(String nombreArchivo) {
        nombreArchivo += "_codes.txt";

        HashMap<String, String> mapa = new HashMap<>();

        Scanner s;

        try {
            s = new Scanner(new File(nombreArchivo));
            while (s.hasNextLine()) {
                String data[] = s.nextLine().split(SEPARATOR);
                mapa.put(data[0], data[1]);
            }
            s.close();

        } catch (FileNotFoundException e) {

            System.out.println("Ha ocurrido un error al leer el archivo. " + e.getMessage());
        }

        return mapa;
    }
}
