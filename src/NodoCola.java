/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class NodoCola<E> {

    public NodoLista<E> primero;
    public NodoLista<E> ultimo;
    public int size;

    public NodoCola() {
        primero = null;
        ultimo = null;
        size = 0;
    }

    public void encolar(NodoLista<E> nodo) {
        if (isEmpty()) {
            primero = nodo;
        } else {
            ultimo.next = nodo;
        }
        ultimo = nodo;
        size++;
    }

    public NodoLista<E> desencolar() {
        if (isEmpty()) {
            return null;
        }
        NodoLista<E> desencolado = primero;
        primero = primero.next;
        if (primero == null) {
            ultimo = null;
        }
        size--;
        return desencolado;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
