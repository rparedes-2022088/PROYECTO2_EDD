 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class TablaHashGenerica<K,V>{
    private NodoHash<K,V>[] casillas;
    private int tamano;
    
    @SuppressWarnings("unchecked")
    public TablaHashGenerica(int tamaño) {
        this.tamano = tamaño;
        // En Java no se pueden crear arreglos de genéricos directamente, se hace un casteo:
        this.casillas = new NodoHash[tamaño]; 
    }

    // Función Hash genérica usando el hashCode nativo de Java adaptado a nuestro tamaño
    public int calcularIndice(K llave) {
        return Math.abs(llave.hashCode()) % tamano;
    }

    public int insertar(K llave, V valor) {

        int indice = calcularIndice(llave);

        NodoHash<K, V> nuevoNodo = new NodoHash<>(llave, valor);

        // Si la casilla está vacía
        if (casillas[indice] == null) {

            casillas[indice] = nuevoNodo;
            return indice;
        }

        NodoHash<K, V> actual = casillas[indice];

        // Revisar si la llave ya existe
        while (actual != null) {

            if (actual.llave.equals(llave)) {

                // Actualiza el valor
                actual.valor = valor;
                return indice;
            }

            // Si es el último nodo
            if (actual.siguiente == null) {
                break;
            }

            actual = actual.siguiente;
        }

        // Insertar al final
        actual.siguiente = nuevoNodo;

        return indice;
    }

    // Buscar el valor por su llave
    public V buscar(K llave) {
        int indice = calcularIndice(llave);
        NodoHash<K, V> actual = casillas[indice];
        while (actual != null) {
            if (actual.llave.equals(llave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
