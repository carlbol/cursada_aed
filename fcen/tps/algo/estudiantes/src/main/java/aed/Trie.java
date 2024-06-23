package aed;
import java.util.*;
public class Trie<V,T> implements DictDigital<V,T> {

    private Nodo raiz;
    private int size;
    private int posicion = 0;
    
    // INVARIANTE:
    // pred InvRep(t: Trie<V,T>) {
    //    (t.size >= 0) y
    //    (raiz = null ||
    //    
    //    raiz.siguiente es una lista enlazada de 256 Nodos
    //    
    //    para todo n de tipo Nodo perteneciente a t ->
    //      para todo i,j de tipo ¿? i != j entonces n.siguiente[i] != n.siguiente[j]
    //    
    //    para todo n1, n2 de tipo Nodo diferentes y pertenecientes a t ->
    //      no existe n3 de tipo Nodo tal que n3 pertenezca a n1.siguiente y a n2.siguiente al mismo tiempo
    //    
    //    no existe n de tipo Nodo tal que todas las posiciones de n.siguiente sean nulas y que n.definición también sea nulo. 
    //    
    //   t.size es igual a la cantidad de nodos con definición distinto a null.
    //   
    //   t.posicion es igual a 0
    //}

    private class Nodo{ 
        ListaEnlazada<Nodo> siguiente;
        T definicion;
        
        Nodo(){                                       // Constructor de Nodo en O(1).
            siguiente = new ListaEnlazada<>();        // O(1)
            for (int i=0; i<256; i++){
                siguiente.agregarAtras(null);;   // O(1)
            }
        }

    }
    public Trie(){           // Constructor de Trie en O(1).
        raiz = new Nodo();   // O(1)
        size = 0;            // O(1)
    }
    public void diccionarioVacio(){ //Crear diccionario vacio en O(1)
        raiz = new Nodo();  // O(1)
        size = 0;           // O(1)
    }

    public int tamano(){    // Obtener el tamaño del trie en O(1).
        return size;        // O(1)
    }

    public T obtener(String clave){ //Obtener en O(|clave|).
        Nodo x = nodo_obtener(raiz, clave, 0);  //O(|clave|)
        if (x == null){     // O(1)
            return null;    // O(1)
        }
        return x.definicion;    // O(1)
    }

    private Nodo nodo_obtener(Nodo x, String clave, int d){  // Nodo_obtener en O(|clave|) por las llamadas recursivas.
        if (x == null){     // O(1)
            return null;    // O(1)
        }
        if (d == clave.length()){   // O(1)
            return x;               // O(1)
        }
        char c = clave.charAt(d);   // O(1)
        return nodo_obtener(x.siguiente.obtener(c), clave, d+1); // O(1) porque la lista está acotada.
    }

    public boolean esta(String clave){            //Está en O(|clave|)
        Nodo x = nodo_obtener(raiz, clave, 0);  // O(|clave|)
        if (x == null || x.definicion == null){   // O(1)
            return false;   // O(1)
        }
        else {
            return true;    // O(1)
        }
    }

    public void definir(String clave, T valor){        // Definir en 
        raiz = nodo_definir(raiz, clave, valor, 0);  // O(|clave|)
        size += 1;                                     // O(1)
    }

    private Nodo nodo_definir(Nodo x, String clave, T valor, int d){  // NodoDefinir en O(|clave|) 
        if (x == null){                               // O(1)
            x = new Nodo();                           // O(1)
        }
        if (d == clave.length()){                     // O(1)         
            x.definicion = valor;                     // O(1)         
            return x;                                 // O(1)         
        }                                                             
        char c = clave.charAt(d);                     // O(1)
        Nodo nodo_a_definir = nodo_definir(x.siguiente.obtener(c), clave, valor, d+1);   // O(|clave|)
        x.siguiente.modificarPosicion(c,nodo_a_definir);                                 // O(1) porque la lista está acotada por 256 caracteres. 
        return x;                                     // O(1)
    }

    

    public String[] claves(){ // Claves en O().
        String[] lista_claves = new String[size];   //O(1)
        enlistar(this.raiz, "", lista_claves);  //O(Sumatoria de la longitud de cada clave)
        posicion = 0;                               //O(1)
        return lista_claves;                        //O(1)
    }

    private void enlistar(Nodo x, String str, String[] lista){ // Charlarlo
        if (x == null){ // O(1)  
            return ;    // O(1) 
        }
        if (x.definicion != null){   // O(1)
            lista[posicion] = str;   // O(1)
            posicion += 1;           // O(1)  
        }
        for (char c = 0; c < 256; c++){                     // Charlarlo
            enlistar(x.siguiente.obtener(c), str+c, lista); // O(Sumatoria de la longitud de cada clave)
        }                                                   // O(Sumatoria de la longitud de las clavas con significado y sin hijos)
    }                                                       // Porque si existen las claves compu y computacion, 
                                                            // el prefijo 'compu' se recorre una sola vez.
    
    public void borrar(String clave){ // Borrar en O(|clave|).
        raiz = borrar_nodo(raiz, clave, 0);
    }

    private Nodo borrar_nodo(Nodo x, String clave, Integer d){  // Borrar nodo en O(|clave|).
        if (x == null){  // O(1)
            return null; // O(1)
        }
        if (d == clave.length()){ // O(1)
            x.definicion = null;  // O(1)
            size -= 1;            // O(1)
        }
        else {
            char c = clave.charAt(d); // O(1)
            Nodo nodo_a_eliminar = borrar_nodo(x.siguiente.obtener(c), clave, d+1); // O(|clave|)
            x.siguiente.modificarPosicion(c, nodo_a_eliminar);                      // O(1) porque la lista está acotada por 256 caracteres. 
        }
        if (x.definicion != null){ // O(1)
            return x;              // O(1)
        }
        for (char c = 0; c < 256; c++){         // O(1)
            if (x.siguiente.obtener(c)!=null){  // O(1) porque la Lista está acotada por 256 caracteres.
                return x;                       // O(1)
            }   
        }
        return null;
    }

}
