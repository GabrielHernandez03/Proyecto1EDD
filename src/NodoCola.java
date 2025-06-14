/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class NodoCola<E> {
    private NodoLista<E> primero;
    private NodoLista<E> ultimo;
    private int size;

    public NodoCola() {
        primero = null;
        ultimo = null;
        size = 0;
    }

    public void encolar(NodoLista<E> nodo) {
        if (esVacia()) {
            primero = nodo;
        } else {
            ultimo.setSiguiente(nodo);
        }
        ultimo = nodo;
        size++;
    }

    public void desencolar(E dato) {
        this.encolar(new NodoLista<>(dato, null));
    }

    public NodoLista<E> desencolar() {
        if (esVacia()) {
            return null;
        }
        NodoLista<E> eliminado = primero;
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        size--;
        return eliminado;
    }

    public boolean esVacia() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public NodoLista<E> getPrimero() {
        return primero;
    }

    public NodoLista<E> getUltimo() {
        return ultimo;
    }
}
