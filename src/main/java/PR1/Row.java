package PR1;

import java.util.ArrayList;
import java.util.List;


public class Row {
    private List<Double> data;
    public Row(){
        data = new ArrayList<>();
    }

    public Row(Double[] fila) {
        new Row();
        for (Double valor: fila)
            data.add(valor);
    }

    public List<Double> getData(){
        return data;
    }
}
