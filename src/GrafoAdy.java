/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel
 */
public class GrafoAdy<E> {

    private boolean[][] matrizAdyacente;
    private final E[] elementos;

    public GrafoAdy() {
        this.elementos = null;
        this.matrizAdyacente = null;
    }

    public GrafoAdy(E[] elementos) {
        this.elementos = elementos;
        int longitudElementos = this.elementos.length;
        this.matrizAdyacente = new boolean[longitudElementos][longitudElementos];
    }

    public E obtener(int n) {
        return this.elementos[n];
    }

    public void enlazarAConB(int a, int b) {
        this.matrizAdyacente[a][b] = true;
    }

    public void enlazarAConBbidireccional(int a, int b) {
        enlazarAConB(a, b);
        enlazarAConB(b, a);
    }

    public GrafoAdy(E[] elementos, int longitudLado) {
        this.elementos = elementos;
        int longitudElementos = this.elementos.length;
        this.matrizAdyacente = new boolean[longitudElementos][longitudElementos];
        int longitudLadoMenosUno = longitudLado - 1;
        int longitudLadoMasUno = longitudLado + 1;
        assert (longitudElementos == (longitudLado * longitudLado));
        for (int i = 0; i < longitudElementos; i++) {
            if (i % longitudLado != 0) {
                this.enlazarAConBbidireccional(i, i - 1);
            }
            if (i / longitudLado != 0) {
                this.enlazarAConBbidireccional(i, i - longitudLado);
            }
            if ((i % longitudLado != 0) && (i / longitudLado != 0)) {
                this.enlazarAConBbidireccional(i, i - longitudLadoMasUno);
            }
            if ((i % longitudLado != 0) && (i / longitudLado != longitudLadoMenosUno)) {
                this.enlazarAConBbidireccional(i, i + longitudLadoMenosUno);
            }
        }
    }

    public boolean esDirigido() {
        for (int i = 0; i < this.elementos.length; i++) {
            for (int j = 0; j < i; j++) {
                if (this.matrizAdyacente[i][j] != this.matrizAdyacente[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean tieneArista(int i, int j) {
        int numVertices = this.elementos.length;
        if (i >= 0 && i < numVertices && j >= 0 && j < numVertices) {
            return matrizAdyacente[i][j];
        }
        return false;
    }

    private boolean pasoBusquedaProfundidad(
            E[] arr,
            int indice,
            int entrada,
            boolean[] tomados) {
        if (indice == arr.length) {
            return true;
        }
        tomados[entrada] = true;
        for (int i = 0; i < this.elementos.length; i++) {
            if (!this.tieneArista(entrada, i) || (this.obtener(i) != arr[indice]) || tomados[i]) {
                continue;
            }
            if (pasoBusquedaProfundidad(arr, indice + 1, i, tomados)) {
                return true;
            }
        }
        tomados[entrada] = false;
        return false;
    }

    public boolean busquedaProfundidadArreglo(E[] arr) {
        boolean[] tomados = new boolean[this.elementos.length];
        for (int i = 0; i < this.elementos.length; i++) {
            if (!this.obtener(i).equals(arr[0])) {
                continue;
            }
            if (pasoBusquedaProfundidad(arr, 1, i, tomados)) {
                return true;
            }
        }
        return false;
    }

    private boolean busquedaAnchuraArregloDesdeEntrada(
            E[] arr,
            int entradaInput) {
        int entrada;
        boolean[] tomados = new boolean[this.elementos.length];
        tomados[entradaInput] = true;
        boolean[] siguienteTomados;
        NodoCola<EntradaColaBusquedaAnchura> colaCapaAnterior = new NodoCola<>();
        NodoCola<EntradaColaBusquedaAnchura> colaCapaSiguiente = new NodoCola<>();
        EntradaColaBusquedaAnchura entradaCola;

        colaCapaAnterior.desencolar(new EntradaColaBusquedaAnchura(entradaInput, tomados));

        for (int j = 1; j < arr.length; j++) {
            while (!colaCapaAnterior.esVacia()) {
                entradaCola = colaCapaAnterior.desencolar().getDato();
                entrada = entradaCola.getEntrada();
                tomados = entradaCola.getVisitados();

                for (int k = 0; k < this.elementos.length; k++) {
                    if (!this.tieneArista(entrada, k) || (this.obtener(k) != arr[j]) || tomados[k]) {
                        continue;
                    }

                    if (j == (arr.length - 1)) {
                        return true;
                    }

                    siguienteTomados = tomados.clone();
                    siguienteTomados[k] = true;
                    colaCapaSiguiente.desencolar(new EntradaColaBusquedaAnchura(k, siguienteTomados));
                }
            }
            colaCapaAnterior = colaCapaSiguiente;
            colaCapaSiguiente = new NodoCola<>();
        }

        return false;
    }

    public boolean busquedaAnchuraArreglo(E[] arr) {
        for (int i = 0; i < this.elementos.length; i++) {
            if (!this.obtener(i).equals(arr[0])) {
                continue;
            }
            if (this.busquedaAnchuraArregloDesdeEntrada(arr, i)) {
                return true;
            }
        }
        return false;
    }

    private ResultadoBusquedaAnchuraVis<E> busquedaAnchuraArregloConVisDesdeEntrada(
            E[] arr,
            int entradaInput) {
        int entrada;
        boolean[] tomados = new boolean[this.elementos.length];
        tomados[entradaInput] = true;
        boolean[] siguienteTomados;
        NodoCola<EntradaColaBusquedaAnchura> colaCapaAnterior = new NodoCola<>();
        NodoCola<EntradaColaBusquedaAnchura> colaCapaSiguiente = new NodoCola<>();
        EntradaColaBusquedaAnchura entradaCola;
        GrafoAdy<E> grafoVisualizacion = new GrafoAdy<>(this.elementos);

        colaCapaAnterior.desencolar(new EntradaColaBusquedaAnchura(entradaInput, tomados));

        for (int j = 1; j < arr.length; j++) {
            while (!colaCapaAnterior.esVacia()) {
                entradaCola = colaCapaAnterior.desencolar().getDato();
                entrada = entradaCola.getEntrada();
                tomados = entradaCola.getVisitados();

                for (int k = 0; k < this.elementos.length; k++) {
                    if (!this.tieneArista(entrada, k) || tomados[k]) {
                        continue;
                    }

                    grafoVisualizacion.enlazarAConB(entrada, k);
                    if (this.obtener(k) != arr[j]) {
                        continue;
                    }

                    if (j == (arr.length - 1)) {
                        return new ResultadoBusquedaAnchuraVis<>(true, grafoVisualizacion);
                    }

                    siguienteTomados = tomados.clone();
                    siguienteTomados[k] = true;
                    colaCapaSiguiente.desencolar(new EntradaColaBusquedaAnchura(k, siguienteTomados));
                }
            }
            colaCapaAnterior = colaCapaSiguiente;
            colaCapaSiguiente = new NodoCola<>();
        }

        return new ResultadoBusquedaAnchuraVis<>(false, grafoVisualizacion);
    }

    public ResultadoBusquedaAnchuraVis<E> busquedaAnchuraArregloConVis(E[] arr) {
        ResultadoBusquedaAnchuraVis<E> resultado = new ResultadoBusquedaAnchuraVis<>(false, new GrafoAdy<E>());
        for (int i = 0; i < this.elementos.length; i++) {
            if (!this.obtener(i).equals(arr[0])) {
                continue;
            }
            resultado = this.busquedaAnchuraArregloConVisDesdeEntrada(arr, i);
            if (resultado.getVisitados()) {
                return resultado;
            }
        }
        return resultado;
    }

    public boolean[][] obtenerMatrizAdyacente() {
        return matrizAdyacente;
    }

    public E[] obtenerElementos() {
        return elementos;
    }

    public int tamaño() {
        return elementos.length;
    }
    
}
