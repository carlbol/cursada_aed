package aed;

public class SistemaSIU {
    private DictDigital<String, Integer> libretas;
    private DictDigital<String, DictDigital<String,Materia>> carreras;
    
    // INVARIANTE:
    // pred InvRep(siu: SistemaSIU) {
    //    
    //    Para toda carrera y para toda materia:
    //
    //    Todo elemento en la lista siu.Carreras[carrera][materia].carrerasVinculadas es un trie cuyas claves tienen como significado una Materia y
    //    
    //    Todo elemento en la lista siu.Carreras[carrera][materia].materiasVinculadas es un nombre de la materia y
    //    
    //    siu.Carreras[carrera][materia].cantInscriptos es menor o igual a |siu.libretas.claves()| y
    //    
    //    Todo elemento en la lista siu.Carreras[carrera][materia].estudiantes pertenece a siu.libretas 
    //}

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        libretas = new Trie<>();                                    // O(1)
        for (int i = 0; i < libretasUniversitarias.length;i++){     // O(E) con E = cantidad de estudiantes
            libretas.definir(libretasUniversitarias[i], 0);   // O(1) porque una libreta está acotada.
        }
        // Hasta este punto la complejidad del SIU es O(E)

        carreras = new Trie<>();                        // O(1)
        for (int j = 0; j < infoMaterias.length;j++){   // Cantidad de materias - M
            ParCarreraMateria[] nombres_materia = infoMaterias[j].getParesCarreraMateria();   // O(1)
            Materia materia_nueva = new Materia();                                            // O(1)

            for (int k = 0; k < nombres_materia.length;k++){ // Cantidad de nombres de materias - Mc
                String nombre_carrera = nombres_materia[k].carrera;         // O(1)
                String nombre_materia = nombres_materia[k].nombreMateria;   // O(1)

                if (!carreras.esta(nombre_carrera)) {                       // O(|nombre_carrera|)
                    //defino la carrera si no lo está
                    DictDigital<String,Materia> materias_carrera = new Trie<>();    // O(1)
                    carreras.definir(nombre_carrera, materias_carrera);             // O(|nombre_carrera|) 
                    
                }

                DictDigital<String,Materia> dicAsociado = carreras.obtener(nombre_carrera); // O(|nombre_carrera|)

                // Hasta este punto, se recorre la clave nombre_carrera 'cantMaterias' veces.
                // Cumpliendo la complejidad de la sumatoria de |c| * |Mc| 
                // donde c pertenece al conjunto de las Carreras y Mc es el conjunto de los nombres de materia de la carrera de grado c.

                //defino el nombre de la materia para una carrera
                dicAsociado.definir(nombre_materia, materia_nueva); // O(|nombre_materia|)

                //agrego a lista de materias vinculadas
                dicAsociado.obtener(nombre_materia).agregarMateriaVinculada(dicAsociado, nombre_materia); // O(|nombre_materia|) AgregarMateriaVinculada es O(1) entonces no afecta.
               
                // Hasta este punto se recorren todos los nombres de una materia, que ciclando con el primer for,
                // se terminan recorriendo todos los nombres de todas las materias.
                // Cumpliendo con la complejidad de la doble sumatoria |n|
                // Con n perteneciente al Nm (Conjunto de nombres de una materia m) y esto sucede 'cantMaterias' veces.
            }
        }	    // Por lo tanto el constructor de SistemaSIU tiene la complejidad de 
                // la sumatoria de |c| * |Mc| + la doble sumatoria |n| + E
    }
    // O(|c| + |m|)
    public void inscribir(String estudiante, String carrera, String materia){
        int cant_inscripciones = libretas.obtener(estudiante); // O(1) porque la longitud de todas las libretas están acotadas.
        libretas.definir(estudiante, cant_inscripciones + 1);  // O(1)
        carreras.obtener(carrera).obtener(materia).inscribir(estudiante); // O(|carrera| + |materia|)
        // Carreras.obtener(carrera) = O(|carrera|)    
        // .obtener(Materia) = O(|materia|)    
        // .cant_inscribir(estudiante) = O(1)
    }

    // O(|c| + |m|)
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        carreras.obtener(carrera).obtener(materia).agregarDocente(cargo);    
        // Carreras.obtener(carrera) = O(|carrera|)    
        // .obtener(Materia) = O(|materia|)    
        // .agregarDocente(cargo) = O(1)
    }

    // O(|c| + |m|)
    public int[] plantelDocente(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).plantelDocente();    
        // Carreras.obtener(carrera) = O(|carrera|)    
        // .obtener(Materia) = O(|materia|)    
        // .plantelDocente() = O(1)
    }

    
    public void cerrarMateria(String materia, String carrera){ //O(|c| + |m| + SUM_n_de_Nm |n| + |e|)
        Materia materia_a_borrar = carreras.obtener(carrera).obtener(materia); // O(|carrera| + |materia|)
        Secuencia<String> estudiantes = materia_a_borrar.estudiantes();  // O(1)     
        while (estudiantes.longitud() > 0){ // O(|estudiantes|)
            String estudiante = estudiantes.sacarPrimero(); // *Corrección* O(|1|)  
            int cant_materias_de_estudiante = libretas.obtener(estudiante);  // O(1) porque la longitud de cada libreta está acotada.
            libretas.definir(estudiante, cant_materias_de_estudiante-1);     // O(1) porque la longitud de cada libreta está acotada.
        }
        while (materia_a_borrar.materiasVinculadas().longitud() > 0){ //materiasVinculadas es O(1) y .longitud() es O(1) -> O(1) + O(|materiasVinculadas|)
            String nombre_materia = materia_a_borrar.materiasVinculadas().sacarPrimero(); // *Corrección* O(|1|)
            materia_a_borrar.carrerasVinculadas().sacarPrimero().borrar(nombre_materia); // *Corrección* carrerasVinculadas es O(1), .sacarPrimero es O(1), .borrar O(|clave|)
        } // la complejidad de este for entonces es O(Sumatoria de la |n| que componen los Nombres de la materia a borrar)
    }

    /*Para evitar el uso de .obtener() -que suponía recorrer las listas enlazadas con un costo de orden O(n)-
     optamos por el uso de la operación .sacarPrimero que borra el primer valor de una lista enlazada 
     y lo retorna en O(1). Con esto, el método cerrarMateria cumple con la complejidad inicial propuesta:
     O(|c| + |m| + SUM_n_de_Nm |n| + |e|)
     */



    // O(|c| + |m|)
    public int inscriptos(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).cant_inscriptos();  // O(|carrera| + |materia|)
        // Carreras.obtener(carrera) = O(|carrera|)    
        // .obtener(Materia) = O(|materia|)    
        // .cant_inscriptos() = O(1)
    }

    // O(|c| + |m|)
    public boolean excedeCupo(String materia, String carrera) {
        int ALUMNOS_POR_PROFESOR = 250; // O(1)       
        int ALUMNOS_POR_JTP = 100;      // O(1) 
        int ALUMNOS_POR_AY1 = 20;       // O(1)
        int ALUMNOS_POR_AY2 = 30;       // O(1)

        int cant_inscriptos = carreras.obtener(carrera).obtener(materia).cant_inscriptos(); // O(|carrera| + |materia|)
        int[] plantel = carreras.obtener(carrera).obtener(materia).plantelDocente();        // O(|carrera| + |materia|)
        int cant_profes = plantel[0]; // O(1)
        int cant_jtps = plantel[1];   // O(1)  
        int cant_ay1 = plantel[2];    // O(1)  
        int cant_ay2 = plantel[3];    // O(1)  
                                                                    
        boolean hay_suficientes_profes = cant_profes != 0 && (cant_inscriptos / (float) cant_profes) <= ALUMNOS_POR_PROFESOR; // O(1)
        boolean hay_suficientes_jtps = cant_jtps != 0 && (cant_inscriptos / (float) cant_jtps) <= ALUMNOS_POR_JTP;            // O(1)
        boolean hay_suficientes_ay1 = cant_ay1 != 0 && (cant_inscriptos / (float) cant_ay1) <= ALUMNOS_POR_AY1;               // O(1)
        boolean hay_suficientes_ay2 = cant_ay2 != 0 && (cant_inscriptos / (float) cant_ay2) <= ALUMNOS_POR_AY2;               // O(1)

        if (cant_inscriptos == 0) return false; // O(1)
        if (hay_suficientes_profes && hay_suficientes_jtps && hay_suficientes_ay1 && hay_suficientes_ay2) return false;     // O(1)
        return true;  // O(1)
    }


    
    public String[] carreras(){
        return carreras.claves(); // O(Sumatoria de logitud de cada carrera)
    }

    public String[] materias(String carrera){  

        return carreras.obtener(carrera).claves(); //O(|carrera| + Sumatoria de logitud de cada materia)
    }

    
    public int materiasInscriptas(String estudiante){ // Materias Inscriptas en O(1)
        return libretas.obtener(estudiante);    
        // libretas.obtener es O(1) porque la longitud de cada libreta está acotada.
    }
}
