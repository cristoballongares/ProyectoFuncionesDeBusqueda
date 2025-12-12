/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Busqueda;

import Objetos.Mercancia;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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
        int M = m[idx];
        
        while(tabla[i]!=null){ // Colision!
            if(tabla[i].getArrivalD().equals(obj.getArrivalD())){ // Fecha y tipo se repite!, eso no es posible
                throw new IllegalArgumentException("La mercancía con fecha " + obj.getArrivalD() + " ya existe en: "+obj.getType());
            }
            // La colision la manejamos con exploracion lineal
            // simplemente, avanzamos a la siguiente posicion hasta que encontremos un lugar vacio!
            i = (i+1)%M;
        }
        
        tabla[i] = obj;
        n[idx]++;
        
    }
    
    public void putF(Mercancia obj) {
        if (obj == null) return;
    
        int idx = getIndexType(obj.getType());
        if (idx == -1) return;
    
        verificarFactorCarga(idx);
    
        Mercancia[] tabla = (Mercancia[]) mercancias[idx];
        int hashValue = hash(obj.getArrivalD(), idx);
        int i = hashValue;
        int M = m[idx];
    
        while (tabla[i] != null) {
            if (tabla[i].getArrivalD().equals(obj.getArrivalD())) {
                // SOLUCIÓN: Sobrescribe la mercancía existente en lugar de lanzar error
                tabla[i] = obj;  // Reemplaza con la nueva
                // No increma n[idx] si no se cuentan duplicados como nuevos
                return;  // Termina aquí
            }
            i = (i + 1) % M;  // Exploración lineal
        }
    
        // Si no hay duplicado, inserta normalmente
        tabla[i] = obj;
        n[idx]++;
    }

    public Mercancia[] get(String tipo){
        //return (Mercancia[])mercancias[getIndexType(tipo)]; // O(1):)
        int idx = getIndexType(tipo);
        if (idx == -1) return null;  // Devuelve null si tipo inválido
        return (Mercancia[]) mercancias[idx];
    }
    
    public Mercancia get(String fecha, String tipo){ // alguna en especifico
        int idx = getIndexType(tipo);
        if(idx==-1) return null;

        Mercancia[] tabla = (Mercancia[])mercancias[idx];
        int M = m[idx];
        int hashVal = hash(fecha, idx);
        int i = hashVal;

        int inicio = i; 
        // buscaremos siempre y cuando no encontremos algo vacio
        while(tabla[i]!=null) {
            // Este es?
            if(tabla[i].getArrivalD().equals(fecha)){
                return tabla[i]; // encontradooo
            }
            i=(i+1)%M; // buscamos en la sig posicion
            // si hemos dado toooda la vuelta, salimos
            if(i == inicio) break;
        }

        return null; 
        }
    
    public void erase(String fecha, String tipo){
        int idx = getIndexType(tipo);
        if(idx==-1) throw new IllegalArgumentException("Tipo de mercancia no valida: " + tipo);
        
        int i = hash(fecha, idx);
        int inicio = i;
        int M = m[idx];
        Mercancia[] tabla = (Mercancia[])mercancias[idx];

        while (tabla[i] != null) {
            // es??
            if (tabla[i].getArrivalD().equals(fecha)) {
            tabla[i] = null;
            n[idx]--;
            return; 
        }
        i = (i+1)%M; // avanzamossss
        if (i==inicio) break;
        }
        
    throw new NoSuchElementException("No se encontro ninguna mercancia con la fecha: " + fecha);
    }
    
    public int getIndexType(String tipo){
        return switch (tipo) {
            case "Electrónica" -> 0;
            case "Textiles" -> 1;
            case "Perecederos" -> 2;
            case "Químicos" -> 3;    
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

    /*public void rehashing(int idx){
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
    
    */
    
    public void rehashing(int idx) {
        indiceActualPrimo[idx]++;
        if (indiceActualPrimo[idx] >= primos.length) {
            System.out.println("No hay más primos disponibles para rehashing en índice " + idx);
            return;  // Evita error de índice
        }
        int nuevoM = primos[indiceActualPrimo[idx]];  // Tamaño nuevo del arreglo
        Mercancia[] nuevoArreglo = new Mercancia[nuevoM];
        Mercancia[] anterior = (Mercancia[]) mercancias[idx];
        // Reinsertar TODOS los elementos del arreglo anterior en el nuevo, manejando colisiones
        for (Mercancia merc : anterior) {
            if (merc != null) {
                int nuevoHash = hash(merc.getArrivalD(), idx);  // Recalcula hash con nuevo m
                int i = nuevoHash;
                while (nuevoArreglo[i] != null) {
                    i = (i + 1) % nuevoM;  // Exploración lineal en el nuevo arreglo
                }
                nuevoArreglo[i] = merc;
            }
        }
        // Actualiza el arreglo y el tamaño
        mercancias[idx] = nuevoArreglo;
        m[idx] = nuevoM;
        // n[idx] se mantiene, ya que todos los elementos se reinsertan
    }
    
}
