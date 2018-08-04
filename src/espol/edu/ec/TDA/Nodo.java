/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

/**
 *
 * @author MiguelPS
 */
public class Nodo {

    String caracter;
    Integer frecuencia;
    Nodo left, right;
    
    public Nodo(){
        
    }
    
    public Nodo(String caracter, Integer frecuencia) {
        this.caracter= caracter;
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() {
        return "{"+caracter + ", " +frecuencia + '}';
    }
    
    
   
    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getLeft() {
        return left;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public Nodo getRight() {
        return right;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }
    
    
}
