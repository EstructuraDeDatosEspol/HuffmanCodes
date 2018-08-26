/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.caracter);
        hash = 53 * hash + Objects.hashCode(this.frecuencia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (!Objects.equals(this.caracter, other.caracter)) {
            return false;
        }
        if (!Objects.equals(this.frecuencia, other.frecuencia)) {
            return false;
        }
 
        return true;
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
