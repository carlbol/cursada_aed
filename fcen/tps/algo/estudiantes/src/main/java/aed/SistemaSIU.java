package aed;

public class SistemaSIU {
    private DictDigital<String, Integer> libretas;
    private DictDigital<String, DictDigital<String,Materia>> carreras;
    

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        libretas = new Trie<>();
        for (int i = 0; i < libretasUniversitarias.length;i++){
            libretas.definir(libretasUniversitarias[i], 0);
        }

        carreras = new Trie<>();
        for (int j = 0; j < infoMaterias.length;j++){
            ParCarreraMateria[] nombres_materia = infoMaterias[j].getParesCarreraMateria();
            Materia materia_nueva = new Materia();
            for (int k = 0; k < nombres_materia.length;k++){
                String nombre_carrera = nombres_materia[k].carrera;
                String nombre_materia = nombres_materia[k].nombreMateria;
                if (!carreras.esta(nombre_carrera)) {
                    //defino la carrera si no lo está
                    DictDigital<String,Materia> materias_carrera = new Trie<>();
                    carreras.definir(nombre_carrera, materias_carrera);
                    
                }
                DictDigital<String,Materia> dicAsociado = carreras.obtener(nombre_carrera);
                //defino el nombre de la materia para una carrera
                dicAsociado.definir(nombre_materia, materia_nueva);
                //agrego a lista de materias vinculadas
                dicAsociado.obtener(nombre_materia).agregarMateriaVinculada(dicAsociado, nombre_materia);
               



            }
        }	    
    }

    public void inscribir(String estudiante, String carrera, String materia){
        int cant_inscripciones = libretas.obtener(estudiante);
        libretas.definir(estudiante, cant_inscripciones + 1);
        carreras.obtener(carrera).obtener(materia).inscribir(estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        carreras.obtener(carrera).obtener(materia).agregarDocente(cargo);    
    }

    public int[] plantelDocente(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).plantelDocente();    
    }

    public void cerrarMateria(String materia, String carrera){
        Materia materia_a_borrar = carreras.obtener(carrera).obtener(materia);
        Secuencia<String> estudiantes = materia_a_borrar.estudiantes();
        for (int i = 0;i < estudiantes.longitud();i++){
            String estudiante = estudiantes.obtener(i);
            int cant_materias_de_estudiante = libretas.obtener(estudiante);
            libretas.definir(estudiante, cant_materias_de_estudiante-1);
        }
        for (int i = 0;i < materia_a_borrar.materiasVinculadas().longitud();i++){
            String nombre_materia = materia_a_borrar.materiasVinculadas().obtener(i);
            materia_a_borrar.carrerasVinculadas().obtener(i).borrar(nombre_materia);
        }
    }

    public int inscriptos(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).cant_inscriptos();	    
    }

    public boolean excedeCupo(String materia, String carrera) {
        final int ALUMNOS_POR_PROFESOR = 250;
        final int ALUMNOS_POR_JTP = 100;
        final int ALUMNOS_POR_AY1 = 20;
        final int ALUMNOS_POR_AY2 = 30;

        int cant_inscriptos = carreras.obtener(carrera).obtener(materia).cant_inscriptos();
        int[] plantel = carreras.obtener(carrera).obtener(materia).plantelDocente();
        int cant_profes = plantel[0];
        int cant_jtps = plantel[1];
        int cant_ay1 = plantel[2];
        int cant_ay2 = plantel[3];
    
        boolean hay_suficientes_profes = cant_profes != 0 && (cant_inscriptos / (float) cant_profes) <= ALUMNOS_POR_PROFESOR;
        boolean hay_suficientes_jtps = cant_jtps != 0 && (cant_inscriptos / (float) cant_jtps) <= ALUMNOS_POR_JTP;
        boolean hay_suficientes_ay1 = cant_ay1 != 0 && (cant_inscriptos / (float) cant_ay1) <= ALUMNOS_POR_AY1;
        boolean hay_suficientes_ay2 = cant_ay2 != 0 && (cant_inscriptos / (float) cant_ay2) <= ALUMNOS_POR_AY2;

        if (cant_inscriptos == 0) return false;
        if (hay_suficientes_profes && hay_suficientes_jtps && hay_suficientes_ay1 && hay_suficientes_ay2) return false;
        return true;
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

    
    public String[] carreras(){
        Secuencia<String> listado = carreras.claves();    
        String[] listado_str = new String[listado.longitud()];
        for (int i = 0; i < listado.longitud();i++){
            listado_str[i] = listado.obtener(i);
        }
        return listado_str;
    }

    public String[] materias(String carrera){
        
        Secuencia<String> listado = carreras.obtener(carrera).claves(); 
        String[] listado_str = new String[listado.longitud()];
        for (int i = 0; i < listado.longitud();i++){
            listado_str[i] = listado.obtener(i);
        }
        return listado_str;  
    }

    public int materiasInscriptas(String estudiante){
        return libretas.obtener(estudiante);    
    }
}
