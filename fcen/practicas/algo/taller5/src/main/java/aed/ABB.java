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
                if (valor.compareTo(elem) == 0) {
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
            Nodo padre = actual.arriba;
            while (actual.valor.compareTo(elem) != 0){
                if (actual.valor.compareTo(elem) > 0){
                    actual = actual.izq;
                } 
                else {
                    actual = actual.der;
                }
                padre = actual.arriba;
            }
            if (actual.cant_hijos() == 0){
                if (padre == null){
                    actual = null;
                }
                else if (padre.der != null && actual.valor.compareTo(padre.der.valor) == 0){
                    padre.der = null;
                }
                else if (padre.izq != null && actual.valor.compareTo(padre.izq.valor) == 0){
                    padre.izq = null;
                }
            }
            else if (actual.cant_hijos() == 1){
                if (actual.izq == null){
                    if (padre == null){
                        _raiz = actual.der;
                        _raiz.arriba = null;
                    }
                    else if (padre.der != null && actual.valor.compareTo(padre.der.valor) == 0){
                        padre.der = actual.der;
                        actual.der.arriba = padre; 
                    } 
                    else if (padre.izq != null && actual.valor.compareTo(padre.izq.valor) == 0) {
                        padre.izq = actual.der;
                        actual.der.arriba = padre;
                    }
                    
                }
                else {
                    if (padre == null){
                        _raiz = actual.izq;
                        _raiz.arriba = null;
                    }
                    if (padre.der != null && actual.valor.compareTo(padre.der.valor) == 0){
                        padre.der = actual.izq;
                        actual.izq.arriba = padre; 
                    } 
                    else if (padre.izq != null && actual.valor.compareTo(padre.izq.valor) == 0) {
                        padre.izq = actual.izq;
                        actual.izq.arriba = padre;
                    }
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
            _raiz.insertar_nodo(elem);
        }
        
      }
    }
    
    

    public boolean pertenece(T elem){
        boolean res = false;
        if (_cardinal > 0){
            res = this._raiz.pertenece_nodo(elem); 
        }
        return res;
    }


    public void eliminar(T elem){
        if (pertenece(elem)){
            _cardinal -= 1;
            _raiz.eliminar_nodo(elem);
        }
    }

    public String toString(){
        Iterador<T> it = this.iterador();
        StringBuffer str = new StringBuffer();
        str.append("{");
        while (it.haySiguiente()){
            T actual = it.siguiente();
            str.append(actual);
            if (actual.compareTo(this.maximo()) != 0){
            str.append(",");
            }
        }
        str.append("}");
        return str.toString();
       /*  int contador = 0;
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
        return str.toString();*/
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual = _raiz;
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
                if (dedito < cardinal()-1){
                _actual = _actual.sucesor_nodo();
                }
                dedito += 1;
            }
            return res;
            }
    }
        

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
