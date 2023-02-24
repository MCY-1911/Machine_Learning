package PR1;

import java.util.ArrayList;
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
        new Table();
        for (String atributo: cabecera)
            headers.add(atributo);
    }
    public List<String> getHeaders() {
        return headers;
    }


    public void add(Row entrada) {
        instancias.add(entrada);
    }

    public Row getRowAt(int rowNumber) {
        return instancias.get(rowNumber);
    }

}
