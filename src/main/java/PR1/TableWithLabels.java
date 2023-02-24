package PR1;

import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table{
    private Map<Integer, String> labelsToIndex;

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
        Row fila = new RowWithLabel(datos, labelsToIndex.size());
        // Para facilitar las cosas la posición del RowWithLabel en instancias será la de su número de clase
        instancias.add(labelsToIndex.size(), fila);
        labelsToIndex.put(labelsToIndex.size(), clase);
    }

    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) instancias.get(rowNumber);
    }

    public String getClass(int numberClass) {
        return labelsToIndex.get(numberClass);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
