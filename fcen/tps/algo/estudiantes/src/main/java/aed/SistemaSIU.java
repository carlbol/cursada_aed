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

            for (int k = 0; k < nombres_materia.length;k++){
                String nombre_carrera = nombres_materia[k].carrera;
                String nombre_materia = nombres_materia[k].nombreMateria;
                if (!carreras.esta(nombre_carrera)) {
                    //defino la carrera si no lo está
                    DictDigital<String,Materia> materias_carrera = new Trie<>();
                    carreras.definir(nombre_carrera, materias_carrera);
                    
                }
                //defino la materia
                Materia materia_nueva = new Materia();
                carreras.obtener(nombre_carrera).definir(nombre_materia, materia_nueva);
            }
        }	    
    }

    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
