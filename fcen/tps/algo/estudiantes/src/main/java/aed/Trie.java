package aed;
import java.util.*;
public class Trie<V,T> implements DictDigital<V,T> {

    private Nodo raiz;
    private int size;
    
    private class Nodo{
        ArrayList<Nodo> siguiente;
        T definicion;
        
        Nodo(){
            siguiente = new ArrayList<Nodo>(256);
            for (int i=0; i<256; i++){
                siguiente.add(null);
            }
        }

    }
    public Trie(){
        raiz = new Nodo();
        size = 0;
    }
    public void diccionarioVacio(){
        raiz = new Nodo();
        size = 0;
    }

    public int tamano(){
        return size;
    }

    public T obtener(String clave){
        Nodo x = nodo_obtener(raiz, clave, 0);
        if (x == null){
            return null;
        }
        return x.definicion;
    }

    private Nodo nodo_obtener(Nodo x, String clave, int d){
        if (x == null){
            return null;
        }
        if (d == clave.length()){
            return x;
        }
        char c = clave.charAt(d);
        return nodo_obtener(x.siguiente.get(c), clave, d+1);
    }

    public boolean esta(String clave){
        Nodo x = nodo_obtener(raiz, clave, 0);
        if (x == null || x.definicion == null){
            return false;
        }
        else {
            return true;
        }
    }

    public void definir(String clave, T valor){
        raiz = nodo_definir(raiz, clave, valor, 0);
        size += 1;
    }

    private Nodo nodo_definir(Nodo x, String clave, T valor, int d){
        if (x == null){
            x = new Nodo();
        }
        if (d == clave.length()){
            x.definicion = valor;
            return x;
        }
        char c = clave.charAt(d);
        Nodo nodo_a_definir = nodo_definir(x.siguiente.get(c), clave, valor, d+1);
        x.siguiente.add(c,nodo_a_definir);
        return x;
    }


    public static void main(String[] args) {
        Trie<String,String> prueba = new Trie<>();
        System.err.println(prueba.tamano());
        prueba.definir("guacamole", "9");
        System.err.println(prueba.obtener("guacamole"));
    }
}
