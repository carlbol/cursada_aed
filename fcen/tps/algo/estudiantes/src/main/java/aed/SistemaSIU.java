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
                //defino el nombre de la materia para una carrera
                carreras.obtener(nombre_carrera).definir(nombre_materia, materia_nueva);
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
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        return carreras.obtener(carrera).obtener(materia).cant_inscriptos();	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
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
