package tratamientodatos.tablas;

import tratamientodatos.filas.Row;
import tratamientodatos.filas.RowWithLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{
    private Map<String, Integer> labelsToIndex;
    public static final TableWithLabels TABLA_LABELS_NULA = new TableWithLabels();
    static {
        List<String> headersTablaLabelsNula = new ArrayList<>();
        headersTablaLabelsNula.add("Esta");
        headersTablaLabelsNula.add("tabla");
        headersTablaLabelsNula.add("con");
        headersTablaLabelsNula.add("etiquetas");
        headersTablaLabelsNula.add("es");
        headersTablaLabelsNula.add("NULA");
        TABLA_LABELS_NULA.headers = headersTablaLabelsNula;
    }

    public TableWithLabels() {
        super();
        labelsToIndex = new HashMap<>();
    }

    public TableWithLabels(String[] cabecera) {
        super(cabecera);
        labelsToIndex = new HashMap<>();
    }
    public void add(Double[] datos, String clase) {
        if (!labelsToIndex.containsKey(clase))
            labelsToIndex.put(clase, labelsToIndex.size());
        Row fila = new RowWithLabel(datos, labelsToIndex.get(clase));
        super.instancias.add(fila);
    }

    @Override
    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) super.getRowAt(rowNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
