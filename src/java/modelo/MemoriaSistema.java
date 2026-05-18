/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class MemoriaSistema {
    private static MemoriaSistema instancia;

    public TablaHashGenerica<Integer, Usuario> bddUsuarios;
    public ArbolMulticamino indiceArbol;

    // El constructor privado evita que se duplique la memoria
    private MemoriaSistema() {
        bddUsuarios = new TablaHashGenerica<>(100); // Tamaño para la Hash
        indiceArbol = new ArbolMulticamino();

    }

    public static MemoriaSistema getInstancia(){
        if(instancia == null){
            instancia = new MemoriaSistema();
        }
        
        return instancia;
    }
}
