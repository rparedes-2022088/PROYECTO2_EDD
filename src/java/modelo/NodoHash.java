/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class NodoHash<K,V> {
    K llave;
    V valor;
    NodoHash<K,V> siguiente; 
    
    public NodoHash(K llave, V valor){
        this.llave = llave;
        this.valor = valor;
        this.siguiente = null;
    }
}
