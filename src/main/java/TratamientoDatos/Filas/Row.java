package TratamientoDatos.Filas;

import java.util.ArrayList;
import java.util.List;


public class Row {
    private List<Double> data;
    public Row(){
        data = new ArrayList<>();
    }

    public Row(Double[] fila) {
        data = new ArrayList<>();
        new Row();
        for (Double valor: fila)
            data.add(valor);
    }

    public List<Double> getData(){
        return data;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (Double dato: data) {
            resultado.append(dato);
            resultado.append("\t|\t");
        }
        return resultado.toString();
    }
}
