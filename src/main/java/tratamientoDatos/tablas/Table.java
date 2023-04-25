package tratamientoDatos.tablas;

import tratamientoDatos.filas.Row;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {

    protected List<String> headers;
    protected List<Row> instancias;
    public static final Table TABLA_NULA = new Table();
    static {
        List<String> headersTABLA_NULA = new ArrayList<>();
        headersTABLA_NULA.add("Esta");
        headersTABLA_NULA.add("tabla");
        headersTABLA_NULA.add("es");
        headersTABLA_NULA.add("NULA");
        TABLA_NULA.headers = headersTABLA_NULA;
    }

    public Table() {
        headers = new ArrayList<>();
        instancias = new ArrayList<>();
    }

    public Table(String[] cabecera) {
        headers = new ArrayList<>();
        instancias = new ArrayList<>();
        Collections.addAll(headers, cabecera);
    }
    public List<String> getHeaders() {
        return headers;
    }

    public List<Row> getInstancias() {
        return instancias;
    }

    public void add(Double[] datos) {
        Row fila = new Row(datos);
        instancias.add(fila);
    }

    public Row getRowAt(int rowNumber) {
        return instancias.get(rowNumber);
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for(String campo : headers) {
            resultado.append(campo);
            resultado.append("\t|\t");
        }
        resultado.append("\n");
        for (Row fila : instancias) {
            resultado.append(fila);
            resultado.append("\n");
        }
        return resultado.toString();
    }

    public int getNumeroFilas(){
        return instancias.size();
    }

    public int getNumeroColumnas(){
        return headers.size();
    }

}
