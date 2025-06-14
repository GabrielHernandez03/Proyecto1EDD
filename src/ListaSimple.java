/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class ListaSimple<E> {

    protected NodoLista<E> primero;

    public ListaSimple() {
        this.primero = null;
    }

    public ListaSimple(E[] datos) {
        if (datos.length == 0) {
            this.primero = null;
            return;
        }

        this.primero = new NodoLista<E>(datos, 0);
    }

    public boolean esVacia() {
        return this.getPrimero() == null;
    }

    public int size() {
        if (this.esVacia()) {
            return 0;
        }
        return this.getPrimero().size();
    }

    public void insertar(E dato) {
        if (this.esVacia()) {
            this.primero = new NodoLista<E>(dato, null);
            return;
        }

        this.getPrimero().insertar(dato);
    }

    public boolean insertar(int n, E elt) {
        if (n < 0) {
            return false;
        }

        if (n == 0) {
            this.insertarPrimero(elt);
            return true;
        }

        if (this.esVacia()) {
            return false;
        }

        return this.getPrimero().insertar(n, elt);
    }

    public boolean insertarFinal(int n, E dato) {
        if (n < 0) {
            return false;
        }
        return this.getPrimero().insertarUltimo(n, dato);
    }

    public E eliminar(int n) {
        if (n != 0) {
            return this.getPrimero().eliminar(n);
        }

        E o = this.getPrimero().getDato();
        this.setPrimero(this.getPrimero().getSiguiente());
        return o;
    }

    public ListaSimple(E dato) {
        this.primero = new NodoLista<E>(dato, null);
    }

    public void insertarPrimero(E elt) {
        NodoLista<E> next = null;
        if (!this.esVacia()) {
            next = this.getPrimero().getSiguiente();
        }
        this.primero = new NodoLista<E>(elt, next);
    }

    public E obtenerNodo(int n) {
        if (this.esVacia() || n < 0) {
            return null;
        }

        return this.getPrimero().buscar(n);
    }

    public NodoLista<E> buscarUltimo() {
        if (this.esVacia()) {
            return null;
        }

        return this.getPrimero().buscarUltimo();
    }

    public E ultimoDato() {
        return this.buscarUltimo().getDato();
    }

    public int indice(Object dato) {
        return this.getPrimero().indice(dato, 0);
    }

    public boolean cambiarDato(int n, E dato) {
        if (n < 0) {
            return false;
        }
        return this.getPrimero().cambiarDato(n, dato);
    }

    public NodoLista<E> getPrimero() {
        return this.primero;
    }

    public void setPrimero(NodoLista<E> primero) {
        this.primero = primero;
    }

    public String mostrarIndices(int indice) throws Exception {
        NodoLista<E> pointer = getPrimero();
        int count = 0;
        while (pointer != null) {
            String prueba = (String) pointer.getDato();
            pointer = pointer.getSiguiente();
            ;
            if (count == indice) {
                return prueba;
            } else {

            }
            count += 1;

        }
        return "";

    }
}
