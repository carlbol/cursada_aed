package aed;

import aed.SistemaSIU.CargoDocente;
import java.util.*;

public class Materia {
    private int cantInscriptos;
    private Secuencia<String> estudiantes;
    private Secuencia<Integer> docentes;
    private Secuencia<Secuencia<String>> materiasVincualdas;

    public void crearMateria(){
        cantInscriptos = 0;
        estudiantes = new ListaEnlazada<>();
        materiasVincualdas = new ListaEnlazada<>();
        docentes = new ListaEnlazada<>();
        for (int i = 0;i < 4; i++){
            docentes.agregarAtras(0);
        }
    }


    public void inscribir(String libreta_universitaria){
        cantInscriptos += 1;
        estudiantes.agregarAdelante(libreta_universitaria);
    }

    public int cant_inscriptos(){
        return cantInscriptos;
    }

    public void agregarDocente(CargoDocente cargo){
        if (cargo == cargo.PROF){
            int cant_actual = docentes.obtener(0) + 1;
            docentes.modificarPosicion(0, cant_actual);
        }
        if (cargo == cargo.JTP){
            int cant_actual = docentes.obtener(1) + 1;
            docentes.modificarPosicion(1, cant_actual);
        }
        if (cargo == cargo.AY1){
            int cant_actual = docentes.obtener(2) + 1;
            docentes.modificarPosicion(2, cant_actual);
        }
        if (cargo == cargo.AY2){
            int cant_actual = docentes.obtener(3) + 1;
            docentes.modificarPosicion(3, cant_actual);
        }
    }

    public Secuencia<Integer> plantelDocente(){
        return docentes;
    }

}
