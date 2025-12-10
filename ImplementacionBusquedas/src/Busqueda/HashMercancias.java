/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda;

import Objetos.Mercancia;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class HashMercancias {

    private Object[] mercancias; // OJO, aca solo guardaremos ARREGLOS DE MERCANCIAS
    private int m[], n[] = {0,0,0,0}; // n = tam actual de cada ArrayList<Mercancia>, m = tam maximo de cada ArrayList
    public int indiceActualPrimo[] = {0,0,0,0}; // los 4 tipos de carga empiezan en con el mismo tamaño que es 53
    public int primos[] = {53, 97, 389, 1543, 6151, 24593, 49157, 100003}; // considerar cambiar a ArrayList, para que sea dinamico
    // ya, en vez de hacer dos arreglos distintos, en uno solo basta jeje
    // la idea se puede ejecutar en un mismo arreglo, solo tenemos que manejar bien los casos
    // para aquello, usaremos sobrecarga de metodos y un buen manejo de indices para evitar cosas feas!

    public HashMercancias(){
        mercancias = new Object[4]; // Por ahora, solo trabajaremos con 4, peeeero esto se puede mejorar...
        m = new int[4];
        // llenamoss
            // for(Object o:mercancias) o = new ArrayList<Mercancia>();
            // Hacer lo de arriba no funciona, no pasa el "valor" original, nos da una copia "vacia" y trabajamos sobre ella, asi que no es nada util, y es una copia vacia porque aun no se crea nada :p
        for(int i=0;i<4;i++){
             m[i] = primos[indiceActualPrimo[i]]; //{53,53,53,53}
             mercancias[i] = new Mercancia[m[i]]; // Se crean arreglos con un tamaño fijo de 83
        }
        
    }
    
    public void put(Mercancia obj){ // posible mejora: trabajar con excepciones en caso de tipo de mercancia invalida
        if(obj==null) return;
        
        int idx = getIndexType(obj.getType());
        if(idx==-1) return;
        
        verificarFactorCarga(idx); // verificamos si tenemos espacio suficiente antes de meter una nueva mercancia
        
        Mercancia[] tabla = (Mercancia[])mercancias[idx];
        int hashValue = hash(obj.getArrivalD(), idx); // posicion inicial
        int i = hashValue;
        
        while(tabla[i]!=null){ // Por si agregamos otro objeto con la misma fecha de llegadaa
            if(tabla[i].getArrivalD().equals(obj.getArrivalD())){ // FUNCIONNN TEMPORAL!
                return; 
            }
        }
        
        tabla[i] = obj;
        n[idx]++;
        
    }

    public Mercancia[] get(String tipo){
        return (Mercancia[])mercancias[getIndexType(tipo)]; // O(1):)
    }
    
    public Mercancia get(String fecha, String tipo){ // alguna en especifico
        Mercancia a = null;
        int idx = getIndexType(tipo);

        int indice = hash(fecha, idx);
        Mercancia[] tabla = (Mercancia[])mercancias[idx];
        a = tabla[indice];
        
        if(a!=null && a.getArrivalD().equals(fecha)) return a;
        else return null; // Si no existeee...
    }
    
    public int getIndexType(String tipo){
        return switch (tipo) {
            case "Electronica" -> 0;
            case "Textiles" -> 1;
            case "Perecederos" -> 2;
            case "Quimicos" -> 3;    
            default -> -1;
        };
    }
    
    public void verificarFactorCarga(int idx){
        int N = n[idx], M = m[idx];
        double fc = (N/M);
        if(fc>.7){
            rehashing(idx);
            System.out.println("actualizado indice: "+idx);
            return ;
        }
        System.out.println("Indice "+idx+" sin updt, factor de carga: "+fc*100+"%");
    }

    public int hash(String fecha, int idx){      
        return Integer.parseInt(fecha)%m[idx];
    }

    public void rehashing(int idx){
        indiceActualPrimo[idx]++;
        if(indiceActualPrimo[idx]>primos.length){
            // buscar el siguiente primo mayor que primos[primos.length-1]
        }
        m[idx] = indiceActualPrimo[idx]; // actualizamos el valor de m
        
        // en que consiste rehashing?
        // Primero, sucede cuando se actualiza el tamaño del arreglo (m) ya sea aumentando o disminuyendo
        // con rehashing actualizamos el indice de cada valor del arreglo antiguo tomando en cuenta el nuevo tamaño m
        // por ahora m siempre aumenta, asi que en esta parte de codigo solo trabajaremos en aquello
        
        Mercancia[] actualizado = new Mercancia[m[idx]]; // creamos un nuevo arreglo de tamaño m (nuevo tam)
        Mercancia[] anterior = (Mercancia[]) mercancias[idx];
        // Pasamos los antiguos valores
        int i;
        for(i=0;i<n[idx];i++){ // O(m[idx]), puede llegar a ser 1e5 + 3:(
            if(anterior[i]!=null){
                Mercancia a = anterior[i];
                String fecha = a.getArrivalD();
                int indice = hash(fecha, idx);
                actualizado[indice] = anterior[i];
            }
        }
        
        
    }
    
    
    
}
