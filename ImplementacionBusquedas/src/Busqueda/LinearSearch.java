/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda;

import Objetos.Mercancia;

/**
 *
 * @author salva
 */
public class LinearSearch extends Mercancia{

    public LinearSearch() {
    }
    
    public int linearS(Mercancia[] mercancia, String arrivalD) {
        for (int i = 0; i < mercancia.length; i++) {
            if (mercancia[i].getArrivalD().equals(arrivalD)) {  // Usa getArrivalD() para acceder
                return i;
            }
        }
        return -1; 
    }
}
