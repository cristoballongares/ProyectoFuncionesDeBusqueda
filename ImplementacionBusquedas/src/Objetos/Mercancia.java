/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author todos:)
 */
public class Mercancia { 
    
    String arrivalD, type;
    int units;
    double totalV;
    
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
    
    @Override
    public String toString(){
        return "Mercancia: " + type + ". Fecha de llegada: " + arrivalD;
    }
    
    public String datos(){
        return "Fecha de llegada: "+arrivalD+"\nTipo: "+type+"\nUnidades: "+units+"\nValor total: "+totalV;
    }

    public int compareTo(String fecha) { //Para comparar mercancias
        //Util en la busqueda binaria, segun yo en secuencial tambien :0
        return this.arrivalD.compareTo(fecha);
    }

}
