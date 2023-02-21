package PR1;

import java.util.List;

public class Table {

    private List<String> headers;
    private  List<Row> instancias;

    public Row getRowAt(int index) {
        return instancias.get(index);
    }

}
