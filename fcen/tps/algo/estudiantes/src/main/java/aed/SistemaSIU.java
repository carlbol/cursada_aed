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
        libretas = new Trie<>();                                    
        for (int i = 0; i < libretasUniversitarias.length;i++){     
            libretas.definir(libretasUniversitarias[i], 0);   //O(1)
        }
        /* Primer parte del método:
        Se crea el trie libretas y se itera tantas veces como libretas haya en libretasUniversitarias 
        (este número es E). En cada iteración, se define en el trie la libreta en la posición i; 
        esta definición se hace en tiempo constante por estar estas libretas acotadas. 
        La definición de todas las libretas en el trie va a ser de orden O(|E|)
         */

        carreras = new Trie<>(); 
        //este for va a iterar tantas veces como materias distintas haya                       
        for (int j = 0; j < infoMaterias.length;j++){   
            ParCarreraMateria[] nombres_materia = infoMaterias[j].getParesCarreraMateria();   //O(1)
            Materia materia_nueva = new Materia();                                            //O(1)        
            // este va a iterar igual a la cantidad de nombres de una materia
            for (int k = 0; k < nombres_materia.length;k++){  
                String nombre_carrera = nombres_materia[k].carrera;         
                String nombre_materia = nombres_materia[k].nombreMateria;   

                if (!carreras.esta(nombre_carrera)) {                       // Comprobar si una clave está lleva |nombre_carrera|
                    //defino la carrera si no lo está
                    DictDigital<String,Materia> materias_carrera = new Trie<>();    // O(1)
                    carreras.definir(nombre_carrera, materias_carrera);             // Definir es un método de orden O(|nombre_carrera|)
                    
                }

                DictDigital<String,Materia> dicAsociado = carreras.obtener(nombre_carrera); // O(|nombre_carrera|)
                dicAsociado.definir(nombre_materia, materia_nueva); // O(|nombre_materia|)
                //agrego a lista de materias vinculadas
                dicAsociado.obtener(nombre_materia).agregarMateriaVinculada(dicAsociado, nombre_materia); // .obtener se hace en O(|nombre_materia)
                                                                                    //mientras qye agregarMateriaVinculada se hace constante.
               
        /*Segunda parte del método:
        Sabemos que infoMaterias.length == |M| (cardinal del conjunto de todas las materias) 
        De cada iteración del primer loop vamos a obtener una lista nombres_materia con los distintos
        nombres de una misma materia y su respectiva carrera (nombres_materia.length == |N_m|).
        En el segundo loop vamos a iterar |N_m| veces.
        Durante este segundo loop puede darse el caso que una materia no tenga a su par carrera;
        para saber si la carrera está definida se usa carreras.esta(c) (método que se hace en orden 
        de O(c), siendo c el nombre de una carrera pertenciente a C -el conjunto de carreras-) y, de
        no estarlo, se define con .definir (también en O(c)).
        Ya teniendo la c definida como clave cuyo valor es un trie, se procede a definir la materia n
        en dicho trie en O(|n|), |n| == longitud del nombre de la materia para cierta carrera.
        Para tener una forma de representar la relación entre las distintas materias m pertenecientes a 
        N_m, se modifica el atributo carrerasVinculadas de la Materia (usando .obtener, O(|n|))

        Así, la complejidad del método completo tiene la forma:
        E + SUMATORIA_m_en_M(SUMATORIA_n_en_Nm(|c_asociada_a_n| + |n|))
        Es decir, tenemos una sumatoria anidada. Para ser más claros usemos un ejemplo para una materia m1
        arbitraria. Para esa m1, tenemos la suma de todos sus nombres distintos junto con la carrera que
        le corresponde: (|c1| + |nombrec1_m1|) + (|c2| + |nombrec2_m1|) + (|c3| + |nombrec3_m1|)
        Para una m2 distinta: (|c1| + |nombrec1_m2|) + (|c3| + |nombrec3_m2|) + (|c4| + |nombrec4_m2|))
        Entonces para todas las materias veríamos algo como:
        |c1| + |nombrec1_m1| + |c2| + |nombrec2_m1| + |c3| + |nombrec3_m1| + 
        |c1| + |nombrec1_m2| +                        |c3| + |nombrec3_m2| + |c4| + |nombrec4_m2| +
        |c1| + |nombrec1_mk| + |c2| + |nombrec2_mk| + |c3| + |nombrec3_mk| + |c4| + |nombrec4_m2| + 
        (... así con todos las n carreras y las materias que le corresponden ... )

        Agrupando las carreras nos queda la suma:
        |c1|*|M_c1| + |c2|*|M_c2| + |c3|*|M_c3|+ ...        (la longitud de cada carrera multiplicada por 
                                                            la cantidad de materias asociadas)
        + |nombre1_m1| + |nombre2_m1| + |nombre3_m1| + ... (los distintos nombres de cada materia)
        |nombre1_m2| + |nombre2_m2| + |nombre3_m2| +...
        |nombre1_mk| + |nombre2_mk| + |nombre3_mk| +...

        + E                                                  (la cantidad de estudiantes)     
        
        Esto es efectivamente lo mismo que la Sumatoria original del enunciado:
        SUMATORIA_c_en_C(|c|*|M_c|) + SUMATORIA_m_en_M(Sumatoria_n_en_Nm(|n|)) + E
        (|c1|*|M_c1| + |c2|*|M_c2| + ... + |ck|*|M_ck|)
        + (|nombre1_m1| + |nombre2_m1| + ... + |nombrep_mq)
        + E  
        */
            }
        }	
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
        while (materia_a_borrar.materiasVinculadas().longitud() > 0){ //materiasVinculadas es O(1) y .longitud() es O(1) -> O(1) + O(|Nm|)
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
