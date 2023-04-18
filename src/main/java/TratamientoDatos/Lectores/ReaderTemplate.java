package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.Table;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReaderTemplate {

    String fileName;
    Table datos;
    Scanner csv;

    public ReaderTemplate(String fileName) {
        super();
        this.fileName = fileName;
    }

    abstract void openSource(String source) throws FileNotFoundException;
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay más línea(s) en el fichero CSV
    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso

    public final Table readTableFromSource() {
        try {
            openSource(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        processHeaders(getNextData());
        while(hasMoreData())
            processData(getNextData());
        closeSource();
        return datos;
    }
}
