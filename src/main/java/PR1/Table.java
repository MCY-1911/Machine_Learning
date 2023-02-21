package PR1;

import java.util.List;

public class Table {

    protected List<String> headers;
    protected List<Row> instancias;

    public Row getRowAt(int index) {
        return instancias.get(index);
    }

}
