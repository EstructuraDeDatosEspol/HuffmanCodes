/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author MiguelPS
 */
public class ArbolHuffman {

    //será el caracter por defecto de los nodos no hojas
    final private String NO_CHARACTER = "-1";
    
    private Nodo raiz;

    public ArbolHuffman() {
        raiz = new Nodo(NO_CHARACTER, 0); 
    }

  
    public void calcularArbol(HashMap<String, Integer> mapaFrecuencias) {

        PriorityQueue<Nodo> frecuencias = new PriorityQueue<>((Nodo n1, Nodo n2) -> (n1.getFrecuencia()-n2.getFrecuencia()));

        for (Map.Entry<String, Integer> entry : mapaFrecuencias.entrySet()) {
            frecuencias.add(new Nodo(entry.getKey(), entry.getValue()));
        }

        if (frecuencias.size() == 1) {
            raiz = frecuencias.poll();
            return;
        } else {
            while (frecuencias.size() > 1) {
            
                Nodo left = frecuencias.poll();
                Nodo right = frecuencias.poll();

                Nodo parent = new Nodo(NO_CHARACTER, left.getFrecuencia() + right.getFrecuencia());
                
                parent.setLeft(left);
                parent.setRight(right);
                frecuencias.offer(parent);
            }
        }
        raiz = frecuencias.poll();

    }

    public HashMap<String, String> calcularCodigos() {

        HashMap<String, String> codigos = new HashMap<>();
        String bin = "";
        calcularCodigos(codigos, raiz, bin);
        return codigos;
    }
    
    /**
    * método privado auxiliar para calcular los códigos binarios de huffman correspondiente
    * a cada caracter
    * 
    * @param codigos representa hashMap que retornará el método publico.
    * @param temp nodo de huffman. Si es una hoja, agrega el caracter y el binario calculado al hashMap 
    * @param bin codigo huffam. Recursivamente agrega: "1" si el caracter se encuentra en left caso contrario "0"
    */
    private void calcularCodigos(HashMap<String, String> codigos, Nodo temp, String bin) {
        

        if (temp.getLeft() == null && temp.getRight() == null) {
            codigos.put(temp.getCaracter(), bin);
            return;
        }

        calcularCodigos(codigos, temp.getLeft() , bin + "1");
        calcularCodigos(codigos, temp.getRight(), bin + "0");

    }


    public String codificar(String textoSinCodificar, HashMap<String, String> codigos) {
        
        StringBuilder temp = new StringBuilder();
        
        for(int i =0 ; i<textoSinCodificar.length();i++){
            temp.append(codigos.get(String.valueOf(textoSinCodificar.charAt(i))));
        }
        
        String result = Util.binarioHexadecimal(temp.toString());
        
        return result;
    }

    public static String decodificar(String textoCodificado, HashMap<String, String> codigos) {
        StringBuilder result = new StringBuilder();
        
        String bin = Util.hexadecimalBinario(textoCodificado);
        String tempBin;
        String caracter;
        
        int inicio = 0;
        int fin;
        
        for(fin = 0; fin <= bin.length(); fin++){
            
            tempBin  = bin.substring(inicio,fin);
            caracter = codigos.get(tempBin);
            
            if(caracter!=null){
                result.append(caracter);
                inicio+=tempBin.length();
            }
        } 
        return result.toString();
    }
    
//    private Nodo searchCaracter(Nodo temp, String bin, int index){
//        
//        if(temp.getLeft() == null && temp.getRight() == null)
//            return temp;
//        if(bin.charAt(index)=='1')
//            return searchCaracter(temp.getLeft(), bin, index++);
//        else
//            return searchCaracter(temp.getRight(), bin, index++);
//    }

    // getter necesario para motrar gráficamente el arbol de huffman con la clase TreePrinter, el cual
    // requiere del nodo root para trabajar
    public Nodo getRaiz() {
        return raiz;
    }

    
}
