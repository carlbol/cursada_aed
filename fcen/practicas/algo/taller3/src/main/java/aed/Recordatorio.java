package aed;

public class Recordatorio {

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha.dia(),fecha.mes());
        _horario = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        Horario mismoHorario = new Horario(_horario.hora(), _horario.minutos());
        return mismoHorario;
    }

    public Fecha fecha() {
        Fecha mismaFecha = new Fecha(_fecha.dia(),_fecha.mes());
        return mismaFecha;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    @Override
    public boolean equals(Object otro) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;
}
