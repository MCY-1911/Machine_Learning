package PR1;

import java.util.Map;

public class TableWithLabels extends Table{
    private Map<String, Integer> labelsToIndex;

    @Override
    public RowWithLabel getRowAt(int index) {
        return instancias.get(index);
    }
}