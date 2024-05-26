package aed;


import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;
    private int _altura;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo arriba;
    
        // Crear Constructor del nodo
        Nodo(T v){
            valor = v;
            izq = null;
            der = null;
            arriba = null;
        }

        
        public T minimo_nodo(){
            if (izq == null){
                return valor;
            }
            else {
                return izq.minimo_nodo();
            }
        }

        public T maximo_nodo(){
            if (der == null){
                return valor;
            }
            else {
                return der.maximo_nodo();
            }
        }

        public void insertar_nodo(T elem){
           if (valor.compareTo(elem) < 0) {
            if (der == null){
                der = new Nodo(elem);
                der.arriba = this;
            } else {
                der.insertar_nodo(elem);
            }
           }
           else if (valor.compareTo(elem) > 0){
            if (izq == null){
                izq = new Nodo(elem);
                izq.arriba = this;
            } else {
            izq.insertar_nodo(elem);
            }
           }
        }

        public boolean pertenece_nodo(T elem){
            if (valor == null) {
                return false;
            } 
            else {
                if (valor == elem) {
                    return true;
                }
                else {
                    if (valor.compareTo(elem)<0){
                        if (der == null){
                            return false;
                        } else {
                        return this.der.pertenece_nodo(elem);
                        }
                    } 
                    else {
                        if (izq == null){
                            return false;
                        }
                        else{
                        return this.izq.pertenece_nodo(elem);
                        }
                    }
                }
            }
        }

        public int cant_hijos(){
            int cant = 0;
            if (izq == null ^ der == null){
                cant = 1;
            }
            else if (izq != null && der != null){
                cant = 2;
            }
            return cant;
        }

        public Nodo sucesor_nodo(){
            Nodo sucesor = null;
            Nodo derecho = this.der;
            Nodo arriba = this.arriba;
            if (derecho != null){
                if (derecho.valor.compareTo(derecho.minimo_nodo()) > 0){
                    while (derecho.valor != derecho.minimo_nodo()) {
                        derecho = derecho.izq;
                    }
                }
                sucesor = derecho;
            }
            else if (arriba != null){
                if (arriba.valor.compareTo(this.valor)>0){
                    sucesor = arriba;
                }
                else {
                    while (arriba.valor.compareTo(this.valor)<0){
                        arriba = arriba.arriba;
                    }
                    sucesor = arriba;
                }
            }
            return sucesor;
        }

        public void eliminar_nodo(T elem){
            Nodo actual = this;
            while (actual.valor != elem){
                if (actual.valor.compareTo(elem) > 0){
                    actual = actual.izq;
                } 
                else {
                    actual = actual.der;
                }
            }
            if (actual.cant_hijos() == 0){
            actual = null;
            }
            else if (actual.cant_hijos() == 1){
                if (actual.izq == null){
                    actual.arriba.der = actual.der;
                    actual.der.arriba = actual.arriba; 
                }
                else {
                    actual.arriba.izq = actual.izq;
                    actual.izq.arriba = actual.arriba;
                }
            }
            else if (actual.cant_hijos() == 2) {
                actual.valor = actual.der.minimo_nodo();
                actual.der.eliminar_nodo(actual.valor);
            }
            
        }

    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
        _altura = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        return _raiz.minimo_nodo();
    }

    public T maximo(){
        return _raiz.maximo_nodo();
    }

    public void insertar(T elem){
      if (_cardinal == 0){
        _raiz = new Nodo(elem);
        _cardinal += 1;
      } else {
        if (!pertenece(elem)){
            _cardinal += 1;
        }
        _raiz.insertar_nodo(elem);
      }
    }

    public static void main(String[] args) {
        ABB<Integer> conjunto = new ABB<Integer>();
        
        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(7);
        conjunto.insertar(6);
        conjunto.insertar(8);
        conjunto.toString();
        conjunto.eliminar(5);
        conjunto.eliminar(7);
        conjunto.toString();
    }

    public boolean pertenece(T elem){
        return this._raiz.pertenece_nodo(elem);
    }


    public void eliminar(T elem){
        _cardinal -= 1;
        _raiz.eliminar_nodo(elem);
    }

    public String toString(){
        int contador = 0;
        Nodo _actual = _raiz;
        while (_actual.valor != _raiz.minimo_nodo()) {
            _actual = _actual.izq;
        }
        StringBuffer str = new StringBuffer();
        str.append("{");
        while (contador < _cardinal){
            str.append(_actual.valor); 
            if (contador < _cardinal-1){
                str.append(",");
                _actual = _actual.sucesor_nodo();
            }
            contador += 1;
        }
        str.append("}");
        return str.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private int dedito;
        ABB_Iterador(){
            dedito = 0;
            while (_actual.valor != _raiz.minimo_nodo()) {
                _actual = _actual.izq;
            }
            
        }

        public boolean haySiguiente() {            
            return dedito < cardinal();
        }
    
        public T siguiente() {
            T res = _actual.valor;
            if (haySiguiente()){
                dedito += 1;
                _actual = _actual.sucesor_nodo();
            }
            return res;
            }
    }
        

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
