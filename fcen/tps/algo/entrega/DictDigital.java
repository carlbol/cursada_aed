package aed;

public interface DictDigital<V,T> {
    // interface no usa el tipo V nunca, está implementado con String.
    public void diccionarioVacio();
    public int tamano();
    public T obtener(String clave);

    public boolean esta(String clave);

    public void definir(String clave, T valor);

    public Secuencia<String> claves();

    public void borrar(String materia);


}
