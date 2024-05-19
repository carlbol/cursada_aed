package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int size;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T elemento) { valor = elemento;}

    }
    
    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        size = 0;
    }

    public int longitud() {
        return size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (size == 0){
            primero = nuevo;
            ultimo = nuevo;
        }
        else {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }
        size += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (size == 0){
            primero = nuevo;
            ultimo = nuevo;
        }
        else {
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        size += 1;
    }

  

    public T obtener(int i) {
        Nodo actual = new Nodo(null);
        actual = primero;
        int contador = 0;
        while (contador < i){
            actual = actual.siguiente;
            contador += 1;
        }
        return actual.valor;
    }

    public static void main(String[] args) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();

        lista.agregarAtras(42);
        lista.agregarAtras(43);
        lista.agregarAtras(44);
        lista.agregarAtras(45);

        lista.modificarPosicion(2, 27);
        lista.longitud();
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        int contador = 0;
        while (contador < i) {
            actual = actual.siguiente;
            contador += 1;
        }
        if (i==0 && size > 1){
            primero = actual.siguiente;
            primero.anterior = null;
        } 
        else if (i==0 && size == 1){
            primero = null;
        }
        else if (i == size - 1) {
            ultimo = actual.anterior;
            ultimo.siguiente = null; 
        }
        else {
            Nodo anterior = actual.anterior;
            Nodo posterior = actual.siguiente;
            anterior.siguiente = posterior;
            posterior.anterior = anterior;
        }
        size -= 1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        int contador = 0;
        while (contador < indice) {
            actual = actual.siguiente;
            contador += 1;
        }
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<T>();
        Nodo copiada = primero;
        for (int i = 0; i < size;i++){
            copia.agregarAtras(copiada.valor);
            copiada = copiada.siguiente;
        }
        copia.size = size;
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
       
        Nodo copiada = lista.primero;
        for (int i = 0; i < lista.size;i++){
            agregarAtras(copiada.valor);
            copiada = copiada.siguiente;
        }
        size = lista.size;

    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        int dedito;
        ListaIterador(){
            dedito = 0;
        }

        public boolean haySiguiente() {
	        return dedito != size;
        }
        
        public boolean hayAnterior() {
	        return dedito > 0 && dedito <= size;
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
