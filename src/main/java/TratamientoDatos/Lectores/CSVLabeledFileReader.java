package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
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
        Double[] filaEnDouble = new Double[filaEnTexto.length-1];
        for (int i = 0; i < filaEnDouble.length; i++) {
            filaEnDouble[i] = Double.parseDouble(filaEnTexto[i]);
        }
        String clase = filaEnTexto[filaEnTexto.length-1];
        ((TableWithLabels) datos).add(filaEnDouble, clase);
    }

}
