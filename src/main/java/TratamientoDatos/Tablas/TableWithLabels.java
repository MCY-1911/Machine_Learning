package TratamientoDatos.Tablas;

import TratamientoDatos.Filas.Row;
import TratamientoDatos.Filas.RowWithLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{
    private Map<String, Integer> labelsToIndex;
    public static final TableWithLabels TABLA_LABELS_NULA = new TableWithLabels();
    static {
        List<String> headersTABLA_LABELS_NULA = new ArrayList<>();
        headersTABLA_LABELS_NULA.add("Esta");
        headersTABLA_LABELS_NULA.add("tabla");
        headersTABLA_LABELS_NULA.add("con");
        headersTABLA_LABELS_NULA.add("etiquetas");
        headersTABLA_LABELS_NULA.add("es");
        headersTABLA_LABELS_NULA.add("NULA");
        TABLA_LABELS_NULA.headers = headersTABLA_LABELS_NULA;
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
        instancias.add(fila);
    }

    @Override
    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) instancias.get(rowNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
