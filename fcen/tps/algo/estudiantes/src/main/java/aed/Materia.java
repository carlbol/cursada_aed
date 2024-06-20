package aed;

import aed.SistemaSIU.CargoDocente;


public class Materia {
    private int cantInscriptos;
    private Secuencia<String> estudiantes;
    private int[] docentes;
    private Secuencia<Secuencia<String>> materiasVincualdas;

    public Materia(){
        cantInscriptos = 0;
        estudiantes = new ListaEnlazada<>();
        materiasVincualdas = new ListaEnlazada<>();
        docentes = new int[4];
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
            docentes[0] = docentes[0] + 1;
        }
        if (cargo == cargo.JTP){
            docentes[1] = docentes[1] + 1;
        }
        if (cargo == cargo.AY1){
            docentes[2] = docentes[2] + 1;
        }
        if (cargo == cargo.AY2){
            docentes[3] = docentes[3] + 1;
        }
    }

    public int[] plantelDocente(){
        return docentes;
    }

}
