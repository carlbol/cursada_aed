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
        ListaEnlazada<Float> lista = new ListaEnlazada<>();

        lista.agregarAdelante(42.0f);
        
        lista.agregarAdelante(41.0f);
      
        lista.agregarAdelante(40.0f);
        
        lista.agregarAdelante(39.0f);
        lista.obtener(0);
        lista.obtener(1);
        lista.obtener(2);
        lista.obtener(3);
    }

    public void eliminar(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada<T> copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
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
