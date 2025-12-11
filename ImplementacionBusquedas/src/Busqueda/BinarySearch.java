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
public class BinarySearch extends Mercancia{

    public BinarySearch() {
    }

    public int binaryS(Mercancia[] mercancia, String arrivalD) {
        int inicio = 0;
        int fin = mercancia.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2; //calculamos el indice del punto central
            int comparacion = mercancia[medio].compareTo(arrivalD);

            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) { 
                inicio = medio + 1;
            } else { 
                fin = medio - 1;
            }
        }
        return -1; 
    }

}
