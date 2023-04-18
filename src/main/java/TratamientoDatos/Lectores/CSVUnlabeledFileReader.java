package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate{
    public CSVUnlabeledFileReader(String fileName) {
        super(fileName);
    }

    @Override
    void openSource(String source) throws FileNotFoundException {
            csv = new Scanner(new File(fileName));
    }

    @Override
    void processHeaders(String headers) {
        String[] cabeceraEnArray = getNextData().split(",");
        datos = new Table(cabeceraEnArray);
    }

    @Override
    void processData(String data) {
        String[] filaEnTexto = getNextData().split(",");
        Double[] filaEnDouble = new Double[filaEnTexto.length];
        int indice = 0;
        for (String dato : filaEnTexto)
            filaEnDouble[indice++] = Double.parseDouble(dato);
        datos.add(filaEnDouble);
    }

    @Override
    void closeSource() {
        csv.close();
    }

    @Override
    boolean hasMoreData() {
      return csv.hasNextLine();
    }

    @Override
    String getNextData() {
        return csv.nextLine();
    }
}
