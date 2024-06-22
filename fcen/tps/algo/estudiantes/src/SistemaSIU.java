package aed;

public class SistemaSIU {
    private DictDigital<String, Integer> libretas;
    private DictDigital<String, DictDigital<String,Materia>> carreras;
    
    // INVARIANTE:
    // pred InvRep(siu: SistemaSIU) {
    //    
    //    siu.Carreras[carrera][materia].carrerasVinculadas: todo elemento en esta Lista es una clave de carreras
    //    
    //    
    //    siu.Carreras[carrera][materia].materiasVinculadas: todo elemento en esta Lista es un nombre de la materia ...ver
    //    
    //    
    //    siu.Carreras[carrera][materia].cantInscriptos: tiene que ser menor a la longitud de siu.libretas
    //    
    //    
    //    siu.Carreras[carrera][materia].estudiantes: todo elemento pertenece a siu.libretas
    //    
    //}

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        libretas = new Trie<>();                                    // O(1)
        for (int i = 0; i < libretasUniversitarias.length;i++){     // O(|E|) con E = cantidad de estudiantes
            libretas.definir(libretasUniversitarias[i], 0);   // O(1) entre comillas
        }

        carreras = new Trie<>();                        // O(1)
        for (int j = 0; j < infoMaterias.length;j++){   // Cantidad de materias
            ParCarreraMateria[] nombres_materia = infoMaterias[j].getParesCarreraMateria();   // O(1)
            Materia materia_nueva = new Materia();                                            // O(1)
            for (int k = 0; k < nombres_materia.length;k++){ // Cantidad de nombres de materias
                String nombre_carrera = nombres_materia[k].carrera;         // O(1)
                String nombre_materia = nombres_materia[k].nombreMateria;   // O(1)
                if (!carreras.esta(nombre_carrera)) {                       // O(|nombre_carrera|)
                    //defino la carrera si no lo está
                    DictDigital<String,Materia> materias_carrera = new Trie<>();    // O(1)
                    carreras.definir(nombre_carrera, materias_carrera);             // DESPUES
                    
                }
                DictDigital<String,Materia> dicAsociado = carreras.obtener(nombre_carrera); // O(|nombre_carrera|)
                //defino el nombre de la materia para una carrera
                dicAsociado.definir(nombre_materia, materia_nueva); // DESPUES
                //agrego a lista de materias vinculadas
                dicAsociado.obtener(nombre_materia).agregarMateriaVinculada(dicAsociado, nombre_materia); // O(|nombre_materia|) AgregarMateriaVinculada es O(1) entonces no afecta.
               



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

    
    public void cerrarMateria(String materia, String carrera){
        Materia materia_a_borrar = carreras.obtener(carrera).obtener(materia); // O(|carrera| + |materia|)
        Secuencia<String> estudiantes = materia_a_borrar.estudiantes();  // O(1)     
        for (int i = 0;i < estudiantes.longitud();i++){ // O(|estudiantes|)
            String estudiante = estudiantes.obtener(i); // O(|estudiantes|)
            int cant_materias_de_estudiante = libretas.obtener(estudiante);  // O(1) porque la longitud de cada libreta está acotada.
            libretas.definir(estudiante, cant_materias_de_estudiante-1);     // O(1) porque la longitud de cada libreta está acotada.
        }
        for (int i = 0;i < materia_a_borrar.materiasVinculadas().longitud();i++){ //materiasVinculadas es O(1) y .longitud() es O(1) -> O(1) + O(|materiasVinculadas|)
            String nombre_materia = materia_a_borrar.materiasVinculadas().obtener(i); // O(|materiasVinculadas|)
            materia_a_borrar.carrerasVinculadas().obtener(i).borrar(nombre_materia); // carrerasVinculadas es O(1), .obtener es O(|materiasVinculadas|), .borrar
        }
    }

    // O(|c| + |m|)
    public int inscriptos(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).cant_inscriptos();  // O(|carrera| + |materia|)
        // Carreras.obtener(carrera) = O(|carrera|)    
        // .obtener(Materia) = O(|materia|)    
        // .cant_inscriptos() = O(1)
    }

    // O(|c| + |m|)
    public boolean excedeCupo(String materia, String carrera) {
        final int ALUMNOS_POR_PROFESOR = 250; // O(1)       // Usar final es legal?
        final int ALUMNOS_POR_JTP = 100;      // O(1) 
        final int ALUMNOS_POR_AY1 = 20;       // O(1)
        final int ALUMNOS_POR_AY2 = 30;       // O(1)

        // usar funcion incriptos y plantelDocente porque ya lo tenemos, pero es lo mismo
        int cant_inscriptos = carreras.obtener(carrera).obtener(materia).cant_inscriptos(); // O(|carrera| + |materia|)
        int[] plantel = carreras.obtener(carrera).obtener(materia).plantelDocente();        // O(|carrera| + |materia|)
        int cant_profes = plantel[0]; // O(1)
        int cant_jtps = plantel[1];   // O(1)  
        int cant_ay1 = plantel[2];    // O(1)  
        int cant_ay2 = plantel[3];    // O(1)  
                                                                    // Suponiendo que el cast a float es O(1)
        boolean hay_suficientes_profes = cant_profes != 0 && (cant_inscriptos / (float) cant_profes) <= ALUMNOS_POR_PROFESOR; // O(1)
        boolean hay_suficientes_jtps = cant_jtps != 0 && (cant_inscriptos / (float) cant_jtps) <= ALUMNOS_POR_JTP;            // O(1)
        boolean hay_suficientes_ay1 = cant_ay1 != 0 && (cant_inscriptos / (float) cant_ay1) <= ALUMNOS_POR_AY1;               // O(1)
        boolean hay_suficientes_ay2 = cant_ay2 != 0 && (cant_inscriptos / (float) cant_ay2) <= ALUMNOS_POR_AY2;               // O(1)

        if (cant_inscriptos == 0) return false; // O(1)
        if (hay_suficientes_profes && hay_suficientes_jtps && hay_suficientes_ay1 && hay_suficientes_ay2) return false;     // O(1)
        return true;  // O(1)
    }

    public static void main(String[] args) {
        
        InfoMateria[] infoMaterias = new InfoMateria[] {
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Intro a la Programación"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos1")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos2")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Técnicas de Diseño de Algoritmos"), new ParCarreraMateria("Ciencias de Datos", "Algoritmos3")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias de la Computación", "Análisis I"), new ParCarreraMateria("Ciencias de Datos", "Análisis I"), new ParCarreraMateria("Ciencias Físicas", "Matemática 1"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático I"), new ParCarreraMateria("Ciencias Matemáticas", "Análisis I") }),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Biológicas", "Química General e Inorgánica 1"), new ParCarreraMateria("Ciencias Químicas", "Química General")}),
            new InfoMateria(new ParCarreraMateria[] {new ParCarreraMateria("Ciencias Matemáticas", "Análisis II"), new ParCarreraMateria("Ciencias de Datos", "Análisis II"), new ParCarreraMateria("Ciencias Físicas", "Matemática 3"), new ParCarreraMateria("Ciencias Químicas", "Análisis Matemático II")})
        };
      String[]  estudiantes = new String[] {"123/23", "321/24", "122/99", "314/81", "391/18", "478/19", "942/20", "291/18", "382/19", "892/22", "658/13", "217/12", "371/11", "294/20"};
    
      SistemaSIU sistema = new SistemaSIU(infoMaterias, estudiantes);

    }

    // O(Sumatoria de logitud de cada carrera).
    public String[] carreras(){
        Secuencia<String> listado = carreras.claves();    // deberia ser O(longitud de la carrera mas larga)
        String[] listado_str = new String[listado.longitud()]; // O(1)
        for (int i = 0; i < listado.longitud();i++){ // O(cant carreras)
            listado_str[i] = listado.obtener(i); // O(i) 
            //listado_str[i] = listado[i];
        }
        return listado_str;
    }

    public String[] materias(String carrera){  // Mismo problema que en carreras.clave().

        Secuencia<String> listado = carreras.obtener(carrera).claves(); // ver complejidad de .claves():
        String[] listado_str = new String[listado.longitud()];
        for (int i = 0; i < listado.longitud();i++){
            listado_str[i] = listado.obtener(i);
        }
        return listado_str;  
    }

    // O(1)
    public int materiasInscriptas(String estudiante){ // Materias Inscriptas en O(1)
        return libretas.obtener(estudiante);    
        // libretas.obtener es O(1) porque la longitud de cada libreta está acotada.
    }
}
