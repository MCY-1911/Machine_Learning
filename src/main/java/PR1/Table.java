package PR1;

import java.util.ArrayList;
import java.util.List;

public class Table {

    protected List<String> headers;
    protected List<Row> instancias;

    public Table(String[] cabecera) {
        instancias = new ArrayList<>();
        for(String campo : cabecera)
            headers.add(campo);
        instancias = new ArrayList<>();
    }

    public void add(String[] entrada) {
        List<String> instancia = new ArrayList<>();
        for(String valor: entrada)
            instancia.add(valor);
        //instancias.add(instancia);
    }

    public Row getRowAt(int index) {
        return instancias.get(index);
    }

}
