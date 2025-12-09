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
public class BinarySearch {

    public BinarySearch() {
    }

    public int binaryS(List<Mercancia> lista, String fecha) {
        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int comparacion = lista.get(medio).compareTo(fecha);

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
