package PR1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{
    private Map<String, Integer> labelsToIndex;


    public TableWithLabels() {
        super();
        labelsToIndex = new HashMap<>();
        headers.add("class-number");
    }

    public TableWithLabels(String[] cabecera) {
        super(cabecera);
        labelsToIndex = new HashMap<>();
        headers.add("class-number");
    }
    public void add(Double[] datos, String clase) {
        if (!labelsToIndex.containsKey(clase))
            labelsToIndex.put(clase, labelsToIndex.size());
        Row fila = new RowWithLabel(datos, labelsToIndex.get(clase));
        instancias.add(fila);
    }

    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) instancias.get(rowNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
