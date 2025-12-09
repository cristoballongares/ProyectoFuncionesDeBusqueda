/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author todos:)
 */
public class Mercancia { // Cambiar nombre de la clase en cuanto lo tengamos decidido
    
    // 1.- Figuras gemometricas
    // Se tiene un arreglo de figuras geometricas, en el arreglo, las figuras se almacenan en orden ascendente tomando en 
    // cuenta el area de dicha figura.
    // mostrar arreglo de figuras geometricas ... (desordenado :p)
    
    // Figura geometrica
    // Nombre (Cuadrado, Rombo, Triangulo, etc...)
    // Perimetro (12, 9.89, 5,...)
    // Area (93.33, 12.4, 3.1416, 9)
    
    // Bri tiene un arreglo de figuras geometricas (como triangulos, cuadrados, poligono, etc), cada figura
    // cuenta con 3 caracteristicas: nombre, perimetro y area. A Bri y a sus amigos les gustan mucho los retos que involucran
    // un orden estricto y figuras geometricas, es por eso, que proponen una serie de desafios a sus compañeros de clase.
    
    // David propone lo siguiente: Si el arreglo esta ordenado a partir del nombre de las figuras geometricas (orden lexicografico)
    // cual y como seria la forma mas eficiente de encontrar la cantidad de apariciones de la figura con nombre "Triangulo" usando busqueda binaria?
    
    // EJEMPLO DE UN ARREGLO:
    // Cuadrado, Cuadrado, Cuadrado, Triangulo, Triangulo, Rombo, Rombo, Hexagono
    
    // Solucion
    // Primero: Encontrar la ultima aparicion de Triangulo usando busqueda binaria y guardar indice
    // Segundo: Encontrar la primera aparicion de Triangulo usando busqueda binaria y guardar indice
    // Tercero: Resta de indices, ultimaAparicion-primeraAparicion
    
    // Salva propone el siguiente reto:
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

    public int compareTo(String fecha) { //Para comparar mercancias
        //Util en la busqueda binaria, segun yo en secuencial tambien :0
        return this.arrivalD.compareTo(fecha);
    }

}
