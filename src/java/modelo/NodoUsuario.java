/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben
 */
public class NodoUsuario {
    Usuario usuario;
    NodoUsuario izquierda;
    NodoUsuario derecha;
    
    public NodoUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}
