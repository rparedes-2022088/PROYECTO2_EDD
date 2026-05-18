/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class ArbolMulticamino {
    private NodoMulticamino raiz;

    public ArbolMulticamino() {
        this.raiz = null;
    }

    // Buscar la posición en la tabla hash usando el árbol
    public int buscarPosicionHash(int idUsuario) {
        return buscarRec(raiz, idUsuario);
    }

    private int buscarRec(NodoMulticamino nodo, int idUsuario) {
        if (nodo == null) return -1; // No encontrado

        // Buscar dentro del nodo actual
        for (int i = 0; i < nodo.cuentaValores; i++) {
            if (idUsuario == nodo.llaves[i]) {
                return nodo.posicionesHash[i]; // Encuentra el indice de la tabla hash
            }
            
        }
        
        if(idUsuario < nodo.llaves[0]){
            return buscarRec(nodo.hijos[0], idUsuario); //hijo izquierdo
        }
        else if(nodo.cuentaValores == 1 || idUsuario < nodo.llaves[1]){
            return buscarRec(nodo.hijos[1], idUsuario); //hijo central
        }
        else{
            return buscarRec(nodo.hijos[2], idUsuario); //hijo derecho
        }
        
    }

    // Insertar básico en un árbol de 3 vías (Vieja escuela)
    public void insertar(int idUsuario, int posicionHash) {
        if (raiz == null) {
            raiz = new NodoMulticamino();
            raiz.llaves[0] = idUsuario;
            raiz.posicionesHash[0] = posicionHash;
            raiz.cuentaValores = 1;
        } else {
            insertarRec(raiz, idUsuario, posicionHash);
        }
    }

    private void insertarRec(NodoMulticamino nodo, int idUsuario, int posicionHash) {
        //si el nodo tiene espacio y es hoja, se mete directo en orden
        boolean esHoja = 
                nodo.hijos[0] == null &&
                nodo.hijos[1] == null &&
                nodo.hijos[2] == null;
        
        if (esHoja) {
            if(nodo.cuentaValores < 2){
                if (idUsuario < nodo.llaves[0]) {
                    //Desplaza elemento actual a la derecha
                    nodo.llaves[1] = nodo.llaves[0];
                    nodo.posicionesHash[1] = nodo.posicionesHash[0];

                    //nuevo elemento en la primera posicion
                    nodo.llaves[0] = idUsuario;
                    nodo.posicionesHash[0] = posicionHash;
                } 
                else {
                    //se coloca en la segunda posición libre
                    nodo.llaves[1] = idUsuario;
                    nodo.posicionesHash[1] = posicionHash;

                }
                nodo.cuentaValores++;
            }
            else{
                derivarAHijo(nodo, idUsuario, posicionHash);
            }
        } 
        else {
            //Si no es una hoja se va hacia los hijos siguiendo la lógica matemática de las llaves
            derivarAHijo(nodo, idUsuario, posicionHash);
        }
    }
    
    private void derivarAHijo(NodoMulticamino nodo, int idUsuario, int posicionHash) {
        if (idUsuario < nodo.llaves[0]) {
            if (nodo.hijos[0] == null) nodo.hijos[0] = new NodoMulticamino();
            insertarRec(nodo.hijos[0], idUsuario, posicionHash);
        } 
        else if (nodo.cuentaValores == 1 || idUsuario < nodo.llaves[1]) {
            if (nodo.hijos[1] == null) nodo.hijos[1] = new NodoMulticamino();
            insertarRec(nodo.hijos[1], idUsuario, posicionHash);
        } 
        else {
            if (nodo.hijos[2] == null) nodo.hijos[2] = new NodoMulticamino();
            insertarRec(nodo.hijos[2], idUsuario, posicionHash);
        }
    }
}
