package aed;

public class InfoMateria{
    private ParCarreraMateria[] paresCarreraMateria;
    
    // INVARIANTE:
    // pred InvRep(i: InfoMateria<ParCarreraMateria>) {
    //    i.ParesCarreraMateria != null 
    //}


    public InfoMateria(ParCarreraMateria[] paresCarreraMateria){ // Constructor en O(1)
        this.paresCarreraMateria = paresCarreraMateria;          // O(1)
    }

    public ParCarreraMateria[] getParesCarreraMateria() {       // GET ParesCarreraMateria en O(1)
        return this.paresCarreraMateria;                        // O(1)
    }
}
