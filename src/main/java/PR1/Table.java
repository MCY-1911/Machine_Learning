package PR1;

import java.util.ArrayList;
import java.util.List;

public class Table {

    protected List<String> headers;
    protected List<Row> instancias;
    public static final Table TABLANULA = new Table();

    public Table() {
        headers = new ArrayList<>();
        instancias = new ArrayList<>();
    }

    public Table(String[] cabecera) {
        new Table();
        for (String atributo: cabecera)
            headers.add(atributo);
    }

    public void add(Row entrada) {
        instancias.add(entrada);
    }

    public Row getRowAt(int rowNumber) {
        return instancias.get(rowNumber);
    }

}
