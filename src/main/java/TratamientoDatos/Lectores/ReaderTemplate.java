package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.Table;
import java.util.Scanner;

public abstract class ReaderTemplate {

    String fileName;
    Table datos;
    Scanner csv;
    String line;


    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay mas línea(s) en el fichero CSV
    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso

    public ReaderTemplate(String fileName) {
        super();
        this.fileName = fileName;
    }

    public final Table readTableFromSource() {
        openSource(fileName);
        processHeaders(getNextData());
        while(hasMoreData())
            processData(getNextData());
        closeSource();
        return datos;
    }
}
