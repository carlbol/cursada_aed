package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int size;

    // Invariante:
    // pred InvRep(l: ListaEnlazada<T>) {
    //    size >= 0 && 
    //    (
    //      (size == 0 && primero == null && ultimo == null) || 
    //      (size == 1 && primero == ultimo)
    //      (size > 1 && primero y ultimo no son nulos y son distintos y
    //      primero.anterior es nulo, ultimo.siguiente es nulo y
    //      existe un n de tipo Nodo igual a primero.siguiente y
    //      existe un n de tipo Nodo igual a ultimo.anterior y
    //      para todo n de tipo Nodo que pertenece a la lista, distino a primero y a ultimo, 
    //      n.siguiente.anterior = n y n.anterior.siguiente = n y
    //      existe un m1,m2 de tipo Nodo tal que n.siguiente = m1 y n.anterior = m2 )
    //   )
    //}
    



    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T elemento) {  // Constructor de Nodo en O(1).
            valor = elemento; // O(1)
        }

    }
    
    public ListaEnlazada() { //Constructor de Lista Enlazada en O(1).
        primero = null;     // O(1)
        ultimo = null;      // O(1)
        size = 0;           // O(1)
    }

    public int longitud() { // Longitud en O(1)
        return size;        // O(1)
    }

    public void agregarAdelante(T elem) { // Agregar Adelante en O(1).
        Nodo nuevo = new Nodo(elem);      // O(1)
        if (size == 0){                   // O(1)
            primero = nuevo;              // O(1)
            ultimo = nuevo;               // O(1)
        }
        else {
            nuevo.siguiente = primero;  // O(1)
            primero.anterior = nuevo;   // O(1)
            primero = nuevo;            // O(1)
        }
        size += 1;                      // O(1)
    }

    public void agregarAtras(T elem) {  // Agregar Adelante en O(1).
        Nodo nuevo = new Nodo(elem);    // O(1)
        if (size == 0){                 // O(1)
            primero = nuevo;            // O(1)
            ultimo = nuevo;             // O(1)
        }
        else {
            nuevo.anterior = ultimo;    // O(1)
            ultimo.siguiente = nuevo;   // O(1)
            ultimo = nuevo;             // O(1)
        }
        size += 1;                      // O(1)
    }

  

    public T obtener(int i) {                       // Obtener en O(i).
        Nodo actual = new Nodo(null);      // O(1)
        actual = primero;                           // O(1)
        int contador = 0;                           // O(1)
        while (contador < i){                       // O(i)
            actual = actual.siguiente;                  // O(1)
            contador += 1;                              // O(1)
        }
        return actual.valor;                        // O(1)
    }

    // COMENTARIO. Esto se borra??
    public static void main(String[] args) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();

        lista.agregarAtras(42);
        lista.agregarAtras(43);
        lista.agregarAtras(44);
        lista.agregarAtras(45);

        lista.modificarPosicion(2, 27);
        lista.longitud();
    }

    public void eliminar(int i) {  // Eliminar en O(i).
        Nodo actual = primero;              // O(1)
        int contador = 0;                   // O(1)
        while (contador < i) {              // O(i)
            actual = actual.siguiente;      //     O(1)
            contador += 1;                  //     O(1)
        }
        if (i==0 && size > 1){              // O(1)
            primero = actual.siguiente;     // O(1)
            primero.anterior = null;        // O(1)
        } 
        else if (i==0 && size == 1){        // O(1)
            primero = null;                 // O(1)
        }
        else if (i == size - 1) {           // O(1)
            ultimo = actual.anterior;       // O(1)
            ultimo.siguiente = null;        // O(1)
        }
        else {
            Nodo anterior = actual.anterior;    // O(1)
            Nodo posterior = actual.siguiente;  // O(1)
            anterior.siguiente = posterior;     // O(1)
            posterior.anterior = anterior;      // O(1)
        }
        size -= 1;  // O(1)                              
    }

    public void modificarPosicion(int indice, T elem) {     // ModificarPosicion en O(indice).
        Nodo actual = primero;                              // O(1)
        int contador = 0;                                   // O(1)
        while (contador < indice) {                         // O(indice)
            actual = actual.siguiente;                          // O(1)
            contador += 1;                                      // O(1)
        }
        actual.valor = elem;                                // O(1)
    }

    public ListaEnlazada<T> copiar() { // Copiar lista en O(size)
        ListaEnlazada<T> copia = new ListaEnlazada<T>();    // O(1)
        Nodo copiada = primero; // O(1)
        for (int i = 0; i < size;i++){         // O(size)
            copia.agregarAtras(copiada.valor); // O(1)
            copiada = copiada.siguiente;       // O(1)
        }
        copia.size = size;                     // O(1)
        return copia;                          
    }

    public ListaEnlazada(ListaEnlazada<T> lista) { // Constructor de Lista con una lista de parametro en O(lista.size).
       
        Nodo copiada = lista.primero;           // O(1)
        for (int i = 0; i < lista.size;i++){    // O(lista.size)
            agregarAtras(copiada.valor);        // O(1)
            copiada = copiada.siguiente;        // O(1)
        }
        size = lista.size;                      // O(1)

    }
    
    @Override
    public String toString() { // ToString en O(size).
        StringBuffer string = new StringBuffer();       // O(1)
        string.append("[");                         // O(1)
        Nodo actual = primero;                          // O(1)
        for (int i=0;i<size;i++){                       // O(size)
            string.append(actual.valor.toString());     // O(1)
            if (size != 1 && i != size -1) {            // O(1)
                string.append(", ");                // O(1)
            }
            actual = actual.siguiente;                  // O(1)
        }
        string.append("]");                         // O(1)
        return string.toString();                       // O(1)
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        int dedito;
        ListaIterador(){
            dedito = 0; // O(1)
        }

        public boolean haySiguiente() {
	        return dedito != size;      // O(1)
        }
        
        public boolean hayAnterior() {
	        return dedito > 0 && dedito <= size;    // O(1)
        }

        public T siguiente() {
            int actual = dedito;    // O(1)
            dedito += 1;            // O(1)
            return obtener(actual); // O(1)
        }
        

        public T anterior() {           
	        int actual = dedito-1;  // O(1)
            dedito -= 1;            // O(1)
            return obtener(actual); // O(1)
        }
    }

    public Iterador<T> iterador() { // O(1)
	    return new ListaIterador(); // O(1)
    }

}
