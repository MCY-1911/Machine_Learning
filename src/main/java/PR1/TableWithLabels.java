package PR1;

import java.util.Map;

public class TableWithLabels extends Table{
    //Extensión de la Clase Table.
    //Adiciones: una nueva columna para la clase.
    private Map<String, Integer> labelsToIndex;

    public TableWithLabels() {
        super();
        headers.add("class-number");
    }

    /*public RowWithLabel getRowAt(int rowNumber) {
        return instancias.get(rowNumber);
    }*/
}
