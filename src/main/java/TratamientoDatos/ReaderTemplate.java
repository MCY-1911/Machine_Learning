package TratamientoDatos;

import TratamientoDatos.Tablas.Table;

public abstract class ReaderTemplate {
    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay mas línea(s) en el fichero CSV
    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso

    public final Table readTableFromSource() {
        return null;
    }
}
