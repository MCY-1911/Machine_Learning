package PR1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{
    private Map<String, Integer> labelsToIndex;
    public static final TableWithLabels TABLA_LABELS_NULA = new TableWithLabels();
    static {
        List<String> headersTABLA_NULA = new ArrayList<>();
        headersTABLA_NULA.add("Esta");
        headersTABLA_NULA.add("tabla");
        headersTABLA_NULA.add("es");
        headersTABLA_NULA.add("NULA");
        TABLA_LABELS_NULA.headers = headersTABLA_NULA;
    }

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
