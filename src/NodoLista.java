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

    public NodoLista() {
        this.elt = null;
        this.next = null;
    }
    public NodoLista(E elt,NodoLista<E> next) {
        this.elt = elt;
        this.next = next;
    }

    public NodoLista(E[] elts,int n) {
        this.elt = elts[n];
        int nextIndex = n + 1;
        if (nextIndex == elts.length) {
            this.next = null;
            return;
        }
        this.next = new NodoLista<E>(elts, nextIndex);
    }

    public E next() {
        return this.next.elt;
    }

    public boolean hasNext() {
        return this.next == null;
    }

    public int size() {
        if (this.next == null) {
            return 1;
        }
        return this.next.size() + 1;
    }

    public void add(E elt) {
        if (this.next != null) {
            next.add(elt);
            return;
        }

        this.next = new NodoLista<E>(elt, null);
    }

    public boolean add(int n, E elt) {
        if (n > 1) {
            if (this.next == null)
                return false;
            return this.next.add(n - 1, elt);
        }

        this.next = new NodoLista<E>(elt, this.next);
        return true;
    }

    public E get(int n) {
        if (n > 0) {
            if (this.next == null)
                return null;
            return this.next.get(n - 1);
        }

        return this.elt;
    }


    public NodoLista<E> getLastNode() {
        if (this.next == null)
            return this;

        return this.next.getLastNode();
    }

    public E getLast() {
        return this.getLastNode().elt;
    }

    public E remove(int n) {
        if (this.next == null)
            return null;

        if (n > 1)
            return this.next.remove(n + 1);

        E o = this.next.elt;
        this.next = this.next.next;
        return o;
    }

    public int indexOf(Object elt, int n) {
        if (this.elt.equals(elt))
            return n;

        if (this.next == null)
            return -1;

        return this.next.indexOf(elt, n + 1);
    }
    
    public boolean set(int n, E elt) {
        if (n == 0) {
            this.elt = elt;
            return true;
        }

        if (this.next == null)
            return false;

        return this.next.set(n - 1, elt);
    }

    public boolean setAsEnd(int n, E elt) {
        if (n == 0) {
            this.elt = elt;
            this.next = null;
            return true;
        }

        if (this.next == null)
            return false;

        return this.next.setAsEnd(n - 1, elt);
    }

}
