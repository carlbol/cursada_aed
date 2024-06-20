package aed;

import aed.SistemaSIU.CargoDocente;


public class Materia {
    private int cantInscriptos;
    private Secuencia<String> estudiantes;
    private int[] docentes;
    private Secuencia<DictDigital<String, Materia>> carrerasVinculadas;
    private Secuencia<String> materiasVinculadas;

    public Materia(){
        cantInscriptos = 0;
        estudiantes = new ListaEnlazada<>();
        carrerasVinculadas = new ListaEnlazada<>();
        materiasVinculadas = new ListaEnlazada<>();
        docentes = new int[4];
    }

    public void agregarMateriaVinculada(DictDigital<String, Materia> materias_de_carrera, String nombre_materia){
           carrerasVinculadas.agregarAtras(materias_de_carrera);
           materiasVinculadas.agregarAtras(nombre_materia);
    }

    public Secuencia<DictDigital<String, Materia>> carrerasVinculadas(){
        return carrerasVinculadas;
    }

    public Secuencia<String> materiasVinculadas(){
        return materiasVinculadas;
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

    public Secuencia<String> estudiantes(){
        return estudiantes;
    }

}
