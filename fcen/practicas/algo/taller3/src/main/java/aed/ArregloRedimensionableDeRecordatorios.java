package aed;

import java.util.Collection;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {

    public ArregloRedimensionableDeRecordatorios() {
        _arreglo = new Recordatorio[0];
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
       _arreglo = vector._arreglo.clone();
    }

    public int longitud() {
        return _arreglo.length;
    }



    public void agregarAtras(Recordatorio j) {
        int largo = _arreglo.length;
        Recordatorio[] nuevoArreglo = new Recordatorio[largo+1];
    
        for (int i = 0;i<largo;i++) {
            nuevoArreglo[i] = _arreglo[i];
        }
        nuevoArreglo[largo] = j;
        _arreglo = nuevoArreglo;
     }

    public Recordatorio obtener(int i) {
        return _arreglo[i];
    }

    public void quitarAtras() {
        int largo = _arreglo.length-1;
        Recordatorio[] nuevoArreglo = new Recordatorio[largo];
        for (int i = 0;i<largo;i++) {
            nuevoArreglo[i] = _arreglo[i];
        }
        _arreglo = nuevoArreglo;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        _arreglo[indice] = valor;

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        int largo = _arreglo.length;
        ArregloRedimensionableDeRecordatorios nuevoArreglo = new ArregloRedimensionableDeRecordatorios();
        for (int i = 0;i<largo;i++) {
            nuevoArreglo.agregarAtras(_arreglo[i]);
        }
        return nuevoArreglo;
    }

    private Recordatorio[] _arreglo;



}



   
