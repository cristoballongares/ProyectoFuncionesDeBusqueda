/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda;

import Objetos.Mercancia;
import java.util.List;

/**
 *
 * @author salva
 */
public class LinearSearch {

    public LinearSearch() {
    }
    
    public int linearS(List<Mercancia> lista, String fecha){
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(fecha)) { 
                return i; 
            }
        }
        return -1; 
    }
    
}
