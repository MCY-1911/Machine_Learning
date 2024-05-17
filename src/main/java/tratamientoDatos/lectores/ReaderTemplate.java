package tratamientodatos.lectores;

import tratamientodatos.tablas.Table;
import tratamientodatos.tablas.TableWithLabels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReaderTemplate {

    String source;
    Table datos;
    Scanner csv = null;

    public ReaderTemplate(String fileName) {
        super();
        this.source = fileName;
    }

    abstract void openSource(String source) throws FileNotFoundException;
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay más línea(s) en el fichero CSV
    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso

    public final Table readTableFromSource() {
        try {
            openSource(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Si el fichero no se puede procesar devolvemos una tabla nula, sea o no con etiquetas
        if (datos==Table.TABLA_NULA || datos== TableWithLabels.TABLA_LABELS_NULA)
            return datos;
        processHeaders(getNextData());
        while(hasMoreData())
            processData(getNextData());
        closeSource();
        return datos;
    }
}
