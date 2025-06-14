/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class NodoLista<E> {

    protected E dato;
    protected NodoLista<E> siguiente;

    public NodoLista() {
        this.dato = null;
        this.siguiente = null;
    }

    public NodoLista(
            E dato,
            NodoLista<E> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public NodoLista(
            E[] dato,
            int n) {
        this.dato = dato[n];
        int indice = n + 1;
        if (indice == dato.length) {
            this.siguiente = null;
            return;
        }
        this.siguiente = new NodoLista<E>(dato, indice);
    }

    public E getDato() {
        return this.dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }


    public NodoLista<E> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoLista<E> siguiente) {
        this.siguiente = siguiente;
    }

    public E datoSiguiente() {
        return this.getSiguiente().getDato();
    }

    public boolean tieneSig() {
        return this.siguiente == null;
    }
    
    public int size() {
        if (this.siguiente == null) {
            return 1;
        }
        return this.siguiente.size() + 1;
    }

    public void insertar(E dato) {
        if (this.siguiente != null) {
            siguiente.insertar(dato);
            return;
        }

        this.siguiente = new NodoLista<E>(dato, null);
    }

    public boolean insertar(int n, E dato) {
        if (n > 1) {
            if (this.siguiente == null)
                return false;
            return this.siguiente.insertar(n - 1, dato);
        }

        this.siguiente = new NodoLista<E>(dato, this.siguiente);
        return true;
    }

    public E buscar(int n) {
        if (n > 0) {
            if (this.siguiente == null)
                return null;
            return this.siguiente.buscar(n - 1);
        }

        return this.dato;
    }

    public NodoLista<E> buscarUltimo() {
        if (this.siguiente == null)
            return this;

        return this.getSiguiente().buscarUltimo();
    }

    
    public E ultimo() {
        return this.buscarUltimo().getDato();
    }

    public E eliminar(int n) {
        if (this.siguiente == null)
            return null;

        if (n > 1)
            return this.getSiguiente().eliminar(n + 1);

        E o = this.getSiguiente().getDato();
        this.setSiguiente(this.getSiguiente().getSiguiente());
        return o;
    }

    public int indice(Object dato, int n) {
        if (this.dato.equals(dato))
            return n;

        if (this.siguiente == null)
            return -1;

        return this.siguiente.indice(dato, n + 1);
    }

    public boolean cambiarDato(int n, E dato) {
        if (n == 0) {
            this.dato = dato;
            return true;
        }

        if (this.siguiente == null)
            return false;

        return this.getSiguiente().cambiarDato(n - 1, dato);
    }

    public boolean insertarUltimo(int n, E dato) {
        if (n == 0) {
            this.dato = dato;
            this.siguiente = null;
            return true;
        }

        if (this.siguiente == null)
            return false;

        return this.getSiguiente().insertarUltimo(n - 1, dato);
    }
}
