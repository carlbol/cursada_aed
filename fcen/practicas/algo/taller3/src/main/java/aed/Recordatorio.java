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
        String mensaje = new String(_mensaje);
        return mensaje;
    }

    @Override
    public String toString() {
        return _mensaje + " @ " + _fecha + " "+ _horario;
    }

    @Override
    public boolean equals(Object otro) {
        boolean is_null = otro == null;
        boolean is_diff_class = this.getClass() != otro.getClass();
        if (is_diff_class || is_null) {
            return false;
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        return _mensaje == otroRecordatorio._mensaje && _fecha == otroRecordatorio._fecha 
        && _horario == otroRecordatorio._horario;
    }

    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;
}
