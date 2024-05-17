package tratamientodatos.lectores;

import tratamientodatos.tablas.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate{
    public CSVUnlabeledFileReader(String fileName) {
        super(fileName);
    }

    @Override
    void openSource(String source) {
        try {
            csv = new Scanner(new File(this.source));
        }catch (FileNotFoundException e) {
            datos = Table.TABLA_NULA;
        }

    }

    @Override
    void processHeaders(String headers) {
        String[] cabeceraEnArray = headers.split(",");
        datos = new Table(cabeceraEnArray);
    }

    @Override
    void processData(String data) {
        String[] filaEnTexto = data.split(",");
        Double[] filaEnDouble = convertADoubleArray(filaEnTexto);
        datos.add(filaEnDouble);
    }

    protected Double[] convertADoubleArray(String[] filaEnTexto) {
        Double[] filaEnDouble = new Double[filaEnTexto.length];
        for (int i = 0; i < filaEnTexto.length; i++) {
            filaEnDouble[i] = Double.parseDouble(filaEnTexto[i]);
        }
        return filaEnDouble;
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
