package tratamientodatos.lectores;

import tratamientodatos.tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{

    public CSVLabeledFileReader(String fileName) {
        super(fileName);
    }


    @Override
    void openSource(String source) {
        try {
            csv = new Scanner(new File(this.source));
        }catch (FileNotFoundException e) {
            datos = TableWithLabels.TABLA_LABELS_NULA;
        }

    }
    @Override
    void processHeaders(String headers) {
        String[] cabeceraEnArray = headers.split(",");
        cabeceraEnArray[cabeceraEnArray.length-1] = "class-number";
        datos = new TableWithLabels(cabeceraEnArray);
    }

    @Override
    void processData(String data) {
        String[] filaEnTexto = data.split(",");
        Double[] filaEnDouble = convertADoubleArray(Arrays.copyOf(filaEnTexto, filaEnTexto.length-1));
        String clase = filaEnTexto[filaEnTexto.length-1];
        ((TableWithLabels) datos).add(filaEnDouble, clase);
    }

}
