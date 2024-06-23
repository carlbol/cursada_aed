package aed;

import aed.SistemaSIU.CargoDocente;


public class Materia {
    private int cantInscriptos;
    private Secuencia<String> estudiantes;
    private int[] docentes;
    private Secuencia<DictDigital<String, Materia>> carrerasVinculadas;
    private Secuencia<String> materiasVinculadas;

    // INVARIANTE:
    // pred InvRep(m: Materia) {
    //    m.cantInscriptos >= 0 y
    //    la longitud de m.estudiantes = m.cantInscriptos, y cada estudiante en m.estudiantes aparece una sola vez.
    //    la longitud de m.docentes es 4 y para todo elemento entre 0 y 3 de m.docentes[elemento] es mayor igual a cero.
    //    en m.carrerasVinculadas no hay elementos repetidos y
    //    la longitud de m.carrerasVinculadas es igual a m.materiasVinculadas y 
    //    para todo elemento i en carrerasVinculadas existe una Materia
    //    carrerasVinculadas[i].obtener(materiasVinculadas[i])
    //    
    //   
    //}


    public Materia(){         // Constructor Materia en O(1).
        cantInscriptos = 0;   // O(1)
        estudiantes = new ListaEnlazada<>();    // O(1)
        carrerasVinculadas = new ListaEnlazada<>();     // O(1)
        materiasVinculadas = new ListaEnlazada<>();     // O(1)
        docentes = new int[4];                          // O(1)
    }

    public void agregarMateriaVinculada(DictDigital<String, Materia> materias_de_carrera, String nombre_materia){ //O(1)
           carrerasVinculadas.agregarAtras(materias_de_carrera); // O(1)  //materias_de_carrera es el trie de carrera.
           materiasVinculadas.agregarAtras(nombre_materia);      // O(1)
    }

    public Secuencia<DictDigital<String, Materia>> carrerasVinculadas(){ //Carreras Vinculadas en O(1)
        return carrerasVinculadas;  // O(1)
    }

    public Secuencia<String> materiasVinculadas(){ //Materias Vinculadas en O(1)
        return materiasVinculadas;  // O(1)
    }




    public void inscribir(String libreta_universitaria){ // Inscribir en O(1)
        cantInscriptos += 1;  // O(1)
        estudiantes.agregarAdelante(libreta_universitaria);  // O(1)
    }

    public int cant_inscriptos(){ // Cantidad de Inscriptos en O(1)
        return cantInscriptos; //O(1)
    }

    public void agregarDocente(CargoDocente cargo){ //Agregar docente en O(1)
        if (cargo == cargo.PROF){  // O(1)
            docentes[0] = docentes[0] + 1;  // O(1)
        }
        if (cargo == cargo.JTP){  // O(1)
            docentes[1] = docentes[1] + 1;  // O(1)
        }
        if (cargo == cargo.AY1){  // O(1)
            docentes[2] = docentes[2] + 1;  // O(1)
        }
        if (cargo == cargo.AY2){  // O(1)
            docentes[3] = docentes[3] + 1;  // O(1)
        }
    }

    public int[] plantelDocente(){ // PlantelDocente O(1)
        return docentes;  // O(1)
    }

    public Secuencia<String> estudiantes(){ //Lista de libretas en O(1)
        return estudiantes;  // O(1)
    }

}
