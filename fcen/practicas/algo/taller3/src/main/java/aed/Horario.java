package aed;

public class Horario {

    public Horario(int hora, int minutos) {
        _hora = hora;
        _minutos = minutos;
    }


    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        StringBuffer hora_y_minuto = new StringBuffer();
        hora_y_minuto.append(_hora);
        hora_y_minuto.append(":");
        hora_y_minuto.append(_minutos);
        return hora_y_minuto.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean is_null = otro == null;
        boolean is_diff_class = this.getClass() != otro.getClass();
        if (is_diff_class || is_null) {
            return false;
        }
        Horario otroHorario = (Horario) otro;
        return _hora == otroHorario._hora && _minutos == otroHorario._minutos;
    }

    private int _hora;
    private int _minutos;

}
