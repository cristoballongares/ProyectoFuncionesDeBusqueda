/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import static java.lang.Math.random;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author todos:)
 */
public class Mercancia { 
    
    String arrivalD, type;
    int units;
    double totalV;
    
    String[] tipos = {"Electrónica", "Textiles", "Perecederos", "Químicos"};
    private Random random = new Random();
    Mercancia[] mercancias;
    
    public Mercancia() {
    }

    public Mercancia(String arrivalD, String type, int units, double totalV) {
        this.arrivalD = arrivalD;
        this.type = type;
        this.units = units;
        this.totalV = totalV;
    }

    public Mercancia(String arrivalD) {
        this.arrivalD = arrivalD;
    }

    public String generarFecha(){
        
        //rango para años de posibles llegadas
        int añoMin = 2025;
        int añoMax = 2026;
        
        //Generamos los años aleatoriamente
        
        int año = random.nextInt(añoMax - añoMin + 1) + añoMin;
        
        //Para generar el mes aleatorio
        int mes = random.nextInt(12) + 1;
        
        // Obtener el número máximo de días para ese mes y año
        int diasEnMes = LocalDate.of(año, mes, 1).lengthOfMonth();
        
        // Generar día aleatorio
        int dia = random.nextInt(diasEnMes) + 1;
        
        // Crear la fecha y ponerlo como DDMMAAAA
        LocalDate fecha = LocalDate.of(año, mes, dia);
        String fechaBuena = String.format("%02d%02d%04d", dia, mes, año);
        
        System.out.println("Fecha aleatoria generada: " + fechaBuena);
        //Para verificar que esté bien hecho el formato
        
        return fechaBuena;
    }
    
    public String generarType(){
        String tipoAl = tipos[random.nextInt(tipos.length)];
        
        return tipoAl;
    }
    
    public int generarUnidades(){
        int uMin = 1;
        int uMax = 1000;
        
        int unidades = random.nextInt(uMax - uMin + 1) + uMin;
        
        return unidades;
    }
    
    public int generarValorT(){
        int valMin = 1000;
        int valMax = 200000;
        
        int valorT = random.nextInt(valMax - valMin + 1) + valMin;
        
        return valorT;
    }
    
    public Mercancia generarMercanciaAl(){
        arrivalD = generarFecha();
        type = generarType();
        units = generarUnidades();
        totalV = generarValorT();
        
        return new Mercancia(arrivalD, type, units, totalV);
    }
    
    // Método para rellenar un arreglo de x productos
    public Mercancia[] rellenarMercancias(int x) {
        mercancias = new Mercancia[x];
        for (int i = 0; i < x; i++) {
            mercancias[i] = generarMercanciaAl();
        }
        return mercancias;
    }
    
    public Mercancia[] getValores(){
        return mercancias;
    }
    
    
    public String getArrivalD() {
        return arrivalD;
    }

    public void setArrivalD(String arrivalD) {
        this.arrivalD = arrivalD;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getTotalV() {
        return totalV;
    }

    public void setTotalV(double totalV) {
        this.totalV = totalV;
    }
    
    //@Override
    //public String toString(){
       // return "Mercancia: " + type + ". Fecha de llegada: " + arrivalD;
    //}
    
    public String datos(){
        return "Fecha de llegada: "+arrivalD+"\nTipo: "+type+"\nUnidades: "+units+"\nValor total: "+totalV;
    }
    
      @Override
    public String toString() {
        return "==================\n" +
             "====Mercancia====\n" +
             "==================\n" +
             "Fecha: " + arrivalD + "\n" +
             "tipo: " + type + "\n" +
             "Unidades: " + units + "\n" +
             "Valor: " + totalV + "\n" +
             "==================";
    }

    public int compareTo(String fecha) { //Para comparar mercancias
        //Util en la busqueda binaria, segun yo en secuencial tambien :0
        return this.arrivalD.compareTo(fecha);
    }
    
    

}
