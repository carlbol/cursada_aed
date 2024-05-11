package aed;

public class Fecha {
    public Fecha(int dia, int mes) {
        _dia = dia;
        _mes = mes;
    }
    

    public Fecha(Fecha fecha) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }

    @Override
    public String toString() {
        StringBuffer dia_y_mes = new StringBuffer();

        dia_y_mes.append(dia().toString());
        dia_y_mes.append("/");
        dia_y_mes.append(mes().toString());
        return dia_y_mes.toString();
    }

    @Override
    public boolean equals(Object otra) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void incrementarDia() {
        if (_dia == diasEnMes(_mes)) {
            _dia = 1;
            if (_mes == 12){
                _mes = 1;
            } else {
                _mes += 1;
            }
            
        } else {
            _dia = _dia  + 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

    private int _dia;
    private int _mes;

}
