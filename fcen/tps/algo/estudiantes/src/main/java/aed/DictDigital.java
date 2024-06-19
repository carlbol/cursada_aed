package aed;

public interface DictDigital<V,T> {

    public void diccionarioVacio();
    public int tamano();
    public T obtener(String clave);

    public boolean esta(String clave);

    public void definir(String clave, T valor);

    public Secuencia<String> claves();


}
