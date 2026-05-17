/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben
 */
public class ArbolBinario {

    private NodoUsuario raiz;

    public void insertar(Usuario usuario){

        NodoUsuario nuevo = new NodoUsuario(usuario);

        if(raiz == null){

            raiz = nuevo;

        } else {

            insertarRecursivo(raiz, nuevo);
        }
    }

    private void insertarRecursivo(NodoUsuario actual, NodoUsuario nuevo){

        if(nuevo.getUsuario().getIdUsuario()
                < actual.getUsuario().getIdUsuario()){

            if(actual.getIzquierda() == null){

                actual.setIzquierda(nuevo);

            } else {

                insertarRecursivo(actual.getIzquierda(), nuevo);
            }

        } else {

            if(actual.getDerecha() == null){

                actual.setDerecha(nuevo);

            } else {

                insertarRecursivo(actual.getDerecha(), nuevo);
            }
        }
    }
}
