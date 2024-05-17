package algoritmos;

import tratamientodatos.filas.Row;
import tratamientodatos.tablas.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveTable {

    public SaveTable() {
        super();
    }
    public void saveTableWithPredictions(Table t, String filename, Kmeans estimador) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.getNumeroFilas(); i++)
            {
                Row row = t.getRowAt(i);
                List<Double> datos = row.getData();
                datos.add((double)estimador.estimate(datos));
                int j=0;
                for (; j<datos.size()-1; j++)
                {
                    fw.write(datos.get(j).toString());
                    fw.write(",");
                }
                fw.write(datos.get(j).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
