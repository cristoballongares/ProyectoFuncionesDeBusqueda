/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Programa;

import Busqueda.BinarySearch;
import Busqueda.HashMercancias;
import Busqueda.LinearSearch;
import Objetos.Mercancia;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class TestPruebaMetodos {
    

    
    private static List<Mercancia> generarDatos(int cantidad) {
    List<Mercancia> lista = new ArrayList<>();
    Random random = new Random(42); 
    

    String[] tipos = {"Electrónica", "Textiles", "Perecederos", "Químicos"};
//para las fechas    
    LocalDate fechaBase = LocalDate.of(2025, 1, 1);
    
    for (int i = 0; i < cantidad; i++) {
        LocalDate fecha = fechaBase.plusDays(i);
        String fechaStr = fecha.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        String tipo = tipos[i % 4];
        
        int unidades = 1 + random.nextInt(100); 
        double valorTotal = 100.0 + random.nextDouble() * 900.0; 
        
        Mercancia mercancia = new Mercancia(fechaStr, tipo, unidades, valorTotal);
        lista.add(mercancia);
    }
    
    return lista;
}//fin metdodo para generear datos

private static HashMercancias generarDatosHash(List<Mercancia> listaMercancias) {
    HashMercancias hashMercancias = new HashMercancias();
    
    for (Mercancia m : listaMercancias) {
        try {
            hashMercancias.put(m);
        } catch (IllegalArgumentException e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }
    
    return hashMercancias;
}//pal hash
    
    
    
    public static void main(String[]args){
    
    //primero vamo a ver si funciona 
  //  int cantidad =10;
    //si funciono   ahora q el usiario de la cantidad
    //en joption q no quiero poner scanner
    int cantidad=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el número de elementos que desee crear"));
    
    //para busqueda binaria
    List<Mercancia> listaMercancias=generarDatos(cantidad);
    //para hash
    HashMercancias hashMercancias=generarDatosHash(listaMercancias);
    
    
    int indicebuscado= cantidad/2;
    Mercancia mercanciaBuscar=listaMercancias.get(indicebuscado);
    String fechabuscada = mercanciaBuscar.getArrivalD();
    String tipobuscado=mercanciaBuscar.getType();
    
    //usando las clases creadas  por salvaaaa
        LinearSearch linearSearch = new LinearSearch();
    BinarySearch binarySearch = new BinarySearch();
    
    //prueba de tiempo
    
    //tiempobusquedalienal
    long il=System.nanoTime();
    Mercancia[] merca=listaMercancias.toArray(new Mercancia[0]);
    int indexLineal = linearSearch.linearS(merca, fechabuscada);
    long fl=System.nanoTime();
    long ttl=fl-il;
    //tiempobusquedabinaria
     long ib=System.nanoTime();
    int indexBinario = binarySearch.binaryS(merca, fechabuscada);
    long fb=System.nanoTime();
    long btl=fb-ib;
    //tiempohash
      long ih=System.nanoTime();
      Mercancia resultado= hashMercancias.get(fechabuscada, tipobuscado);
     long fh=System.nanoTime();
    long htl=fh-ih;
            System.out.println("\n\n");

        System.out.println("Numero de elementos:"+cantidad);
    System.out.println("Resultados");
        System.out.println("Mercancia Buscada:Fecha="+fechabuscada+",Tipo="+tipobuscado+"\n");
        
                System.out.println("--------------------------------------");

            System.out.println("Busqueda Lineal");
                        System.out.println("Resultado:"+(indexLineal !=-1 ?"Encontrado en indice: "+indexLineal:"No se encontro")+"");
                            System.out.println("Tiempo en busqueda lineal:"+ttl+"ns");
                                        System.out.println("\n");

                System.out.println("--------------------------------------");

                            System.out.println("Busqueda Binaria");
                        System.out.println("Resultado:"+(indexBinario !=-1 ?"Encontrado en indice: "+indexBinario:"No se encontro")+"");
                            System.out.println("Tiempo en busqueda Binaria:"+btl+"ns");
                                        System.out.println("\n");
                System.out.println("--------------------------------------");

                                        
                               System.out.println("Busqueda por Hash");
                        System.out.println("Resultado:"+(resultado !=null?"Encontrado en indice: "+resultado:"No se encontro")+"");
                            System.out.println("Tiempo en busquedapor Hash:"+htl+"ns");
                                        System.out.println("\n");
                            

                        
    
    
    
    }//fin main
    
    
    
    
    
    
    
    
    
    
    
    
}//fin clase
