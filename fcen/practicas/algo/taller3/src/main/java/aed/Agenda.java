package aed;

public class Agenda {

    public Agenda(Fecha fechaActual) {
        _fechaActual = new Fecha(fechaActual.dia(),fechaActual.mes());
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorios.agregarAtras(recordatorio);

    }

    @Override
    public String toString() {
    int largo = _recordatorios.longitud();
    StringBuffer str_recordatorios = new StringBuffer();
    for (int i = 0; i < largo;i++) {
        if (_fechaActual.equals(_recordatorios.obtener(i).fecha())) {
        str_recordatorios.append(_recordatorios.obtener(i).toString());
        str_recordatorios.append("\n");
        }
    }
    return _fechaActual.toString() +"\n=====\n" + str_recordatorios;

    }

    public void incrementarDia() {
        _fechaActual.incrementarDia();

    }

    public Fecha fechaActual() {
        Fecha mismaFecha = new Fecha(_fechaActual.dia(),_fechaActual.mes());
        return mismaFecha;
    }

    private Fecha _fechaActual;
    private ArregloRedimensionableDeRecordatorios _recordatorios = new ArregloRedimensionableDeRecordatorios();

}
