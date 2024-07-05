package aed;

public class ParCarreraMateria {
    String carrera;
    String nombreMateria;

    // INVARIANTE:
    // pred InvRep(p: ParCarreraMateria<String,String>) {
    //     p.carrera != null y p.nombreMateria != null
    //}

    public ParCarreraMateria(String carrera, String nombreMateria) { // Constructor de ParCarreraMateria en O(1).
        this.carrera = carrera;             // O(1)
        this.nombreMateria = nombreMateria; // O(1)
    }

    public String getNombreMateria() { // Obtener nombre de la materia del par en O(1).
        return this.nombreMateria;     // O(1)
    }

    public String getCarrera() {    // Obtener la carrera del par en O(1).
        return this.carrera;        // O(1)
    }
}
