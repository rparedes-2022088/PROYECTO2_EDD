/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class NodoMulticamino {
    // Un árbol de orden 3 guarda máximo 2 elementos
    int[] llaves = new int[2];
    int[] posicionesHash = new int[2];
    
    // Y puede tener hasta 3 hijos
    NodoMulticamino[] hijos = new NodoMulticamino[3];
    
    int cuentaValores = 0; // Controla cuántos datos reales tiene el nodo actualmente

    public NodoMulticamino() {
        for (int i = 0; i < 3; i++) {
            hijos[i] = null;
        }
    }
}
