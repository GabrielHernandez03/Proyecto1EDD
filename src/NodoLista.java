/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class NodoLista  <E>{

    public E elt;
    public NodoLista<E> next;

    /**
     * Constructs a node with no element null next pointer.
     */
    public NodoLista() {
        this.elt = null;
        this.next = null;
    }

}
