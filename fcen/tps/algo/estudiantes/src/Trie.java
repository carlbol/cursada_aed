package aed;
import java.util.*;
public class Trie<V,T> implements DictDigital<V,T> {

    private Nodo raiz;
    private int size;
    
    // INVARIANTE:
    // pred InvRep(t: Trie<V,T>) {
    //    (t.size >= 0) y
    //    (raiz = null ||
    //    
    //    raiz.siguiente es una lista enlazada(array) de Nodos de tamaño 256
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
    //}

    private class Nodo{ //Nodo[]
        ListaEnlazada<Nodo> siguiente;
        T definicion;
        
        Nodo(){                                       // Constructor de Nodo en O(1).
            siguiente = new ListaEnlazada<>();        // O(1)
            for (int i=0; i<256; i++){
                siguiente.agregarAtras(null);;   // O(1), porque está acotado.
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

    private Nodo nodo_obtener(Nodo x, String clave, int d){  // VER
        if (x == null){     // O(1)
            return null;    // O(1)
        }
        if (d == clave.length()){   // O(1)
            return x;               // O(1)
        }
        char c = clave.charAt(d);   // O(1)
        return nodo_obtener(x.siguiente.obtener(c), clave, d+1); // O(|clave|)
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

    public void definir(String clave, T valor){        // Definir en O(|clave|) o O(|clave|**2).
        raiz = nodo_definir(raiz, clave, valor, 0);  // O(|clave|) o O(|clave|**2)  
        size += 1;                                     // O(1)
    }

    private Nodo nodo_definir(Nodo x, String clave, T valor, int d){  // NodoDefinir en O(|clave|) o O(|clave|**2)
        if (x == null){                               // O(1)
            x = new Nodo();                           // O(1)
        }
        if (d == clave.length()){                     // O(1)         // Comentario:
            x.definicion = valor;                     // O(1)         // Se llama recursivamente a nodo_definir
            return x;                                 // O(1)         // |clave| veces y además tiene O(|clave|)
        }                                                             // Seria O(|clave| * |clave|) == O(|clave|**2)
        char c = clave.charAt(d);                     // O(1)
        Nodo nodo_a_definir = nodo_definir(x.siguiente.obtener(c), clave, valor, d+1);   // O(|clave|)  PREGUNTAR
        x.siguiente.modificarPosicion(c,nodo_a_definir);                                 // O(|clave|)
        return x;                                     // O(1)
    }

    public ListaEnlazada<String> claves(){ // Claves en O().
        ListaEnlazada<String> lista_claves = new ListaEnlazada<>(); // O(1)
        enlistar(this.raiz, "", lista_claves);
        return lista_claves;
    }

    private void enlistar(Nodo x, String str, ListaEnlazada<String> lista){ // O(mucho) Charlarlo
        if (x == null){ // O(1)  
            return ;    // O(1) 
        }
        if (x.definicion != null){   // O(1)
            lista.agregarAtras(str); // O(1)
        }
        for (char c = 0; c < 256; c++){
            enlistar(x.siguiente.obtener(c), str+c, lista); // O(mucho) Charlarlo pondria O(SUM de |claves|)
        }
    }
    
    public void borrar(String clave){
        raiz = borrar_nodo(raiz, clave, 0);
    }

    private Nodo borrar_nodo(Nodo x, String clave, Integer d){
        if (x == null){  // O(1)
            return null; // O(1)
        }
        if (d == clave.length()){ // O(1)
            x.definicion = null;  // O(1)
            size -= 1;            // O(1)
        }
        else {
            char c = clave.charAt(d); // O(1)
            Nodo nodo_a_eliminar = borrar_nodo(x.siguiente.obtener(c), clave, d+1); //O(|clave|)
            x.siguiente.modificarPosicion(c, nodo_a_eliminar);                      //O(|clave|)
        }
        if (x.definicion != null){ // O(1)
            return x;              // O(1)
        }
        for (char c = 0; c < 256; c++){   // O(mucho)
            if (x.siguiente.obtener(c)!=null){
                return x;
            }   
        }
        return null;
    }


    public static void main(String[] args) {
        Trie<String,String> prueba = new Trie<>();
        System.err.println(prueba.tamano());
        prueba.definir("guacamole", "9");
        System.err.println(prueba.obtener("guacamole"));
        prueba.definir("guacamoles", "83");
        System.err.println(prueba.obtener("guacamoles"));
        prueba.definir("AGUA", "12");
        prueba.definir("AGuA", "12");
        prueba.definir("guarapo", "89");
        prueba.borrar("AGUA");
        prueba.borrar("guarapo");
        System.err.println(prueba.claves());
        System.err.println(prueba.tamano());

    }
}
