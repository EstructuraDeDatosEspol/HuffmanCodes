/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author MiguelPS
 */
public class ArbolHuffman {
    
    Nodo raiz;
    
    
    public void calcularArbol (HashMap<String,Integer> mapa){
        Deque<Map.Entry<String,Integer>> frecuencias = ordenarFrecuancias(mapa);
        
        
    }
    
    public HashMap<String,String> calcularCodigos (){
        HashMap<String, String> codigos = new HashMap<>();
        
        return codigos;
    }
    
    public String codificar(String texto, HashMap<String,String> mapa){
        StringBuilder result= new StringBuilder();
        
        
        return result.toString();
    }
    
    public String decodificar(String texto, HashMap<String,String> mapa){
        StringBuilder result= new StringBuilder();
        
        
        return result.toString();
    }
    
    private LinkedList<Map.Entry<String,Integer>> ordenarFrecuancias(HashMap<String,Integer> mapa){
        LinkedList<Map.Entry<String,Integer>> result = new LinkedList<>();
        
        Collections.sort(result, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue().compareTo(o1.getValue()));
        
        return result;
    }
}
