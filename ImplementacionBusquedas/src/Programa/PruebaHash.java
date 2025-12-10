/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Programa;

import Busqueda.HashMercancias;
import Objetos.Mercancia;

import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author crist
 */
public class PruebaHash {

    /**
     * @param args the command line arguments
     */
//    String arrivalD, type;
//    int units;
//    double totalV;
    
    static HashMercancias listaMercancias = new HashMercancias();
    static String[] tiposDisponibles = {"Electronica", "Textiles", "Perecederos", "Quimicos"};
    static  Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        
        String opciones = "1.- Agregar\n2.- Ver mercancias de algun tipo\n3.-Ver alguna mercancia en especifico\n4.- Eliminar mercanica\n5.- Salir";
        int r = 0;
        do{
            r = Integer.parseInt(JOptionPane.showInputDialog(opciones));
            switch(r){
                case 1: agregarMercancia(); break;
                case 2: verMercanciasTipo(); break;
                case 3: verMercanciaEspecifica(); break;
                case 4: eliminar(); break;
                default: break;
            }
        }while(r!=5);
    }
    
    static String seleccionarTipo() {
        return (String) JOptionPane.showInputDialog(
                null, 
                "Seleccione el tipo de mercancia:", 
                "Tipo de Mercancia", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                tiposDisponibles, 
                tiposDisponibles[0]);
    }
    
    static void agregarMercancia(){
            int t = Integer.parseInt(JOptionPane.showInputDialog("Num de mercancias: "));
            System.out.println("==============");
            for(int i=0;i<t;i++){
                System.out.println("Mercancia: "+(i+1));
                String f = (String)JOptionPane.showInputDialog("Fecha de llegada (DDMMAAAA)");
                System.out.println("Fecha: "+f);
                String tip = seleccionarTipo();
                System.out.println("Tipo: "+tip);
                int u = Integer.parseInt(JOptionPane.showInputDialog("Unidades: "));
                System.out.println("Unidades: "+u);
                double v = Double.parseDouble(JOptionPane.showInputDialog("Valor total: "));
                System.out.println("Valor t: "+v);
                Mercancia a = new Mercancia(f,tip,u,v);
            
                try{
                listaMercancias.put(a);
                } catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "Error al guardar: "+e.getMessage());
                }
                
            }
    }
    
    static void verMercanciasTipo(){
        String tip = seleccionarTipo();
        Mercancia[] mercancias = listaMercancias.get(tip);
        if(mercancias.length==0){
            JOptionPane.showMessageDialog(null, "Aun no hay mercancias de este tipo");
        }
        String msg = "";
        for(Mercancia m: mercancias) if(m!=null)msg+=m.datos()+"\n===========\n";
        
        JOptionPane.showMessageDialog(null, msg);
    }
    
    static void verMercanciaEspecifica(){
        String f = (String)JOptionPane.showInputDialog("Fecha de llegada (DDMMAAAA)");
        String tip = seleccionarTipo();
        
        Mercancia a = listaMercancias.get(f, tip);
        if(a==null){
            JOptionPane.showMessageDialog(null, "La mercancia no existe");
        }
        String msg = "Mercancia: "+a.datos();
        JOptionPane.showMessageDialog(null, msg);

    }
    
    static void eliminar(){   
        String f = JOptionPane.showInputDialog("Fecha a borrar:");
        String tip = seleccionarTipo();
        try {
            listaMercancias.erase(f, tip);
            JOptionPane.showMessageDialog(null, "Eliminado correctamente!!");
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error de tipo: " + e.getMessage());
        }
    }
    
}
    
