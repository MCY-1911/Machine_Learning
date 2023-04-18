package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.TableWithLabels;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{

    public CSVLabeledFileReader(String fileName) {
        super(fileName);
    }


    @Override
    void processHeaders(String headers) {
        String[] cabeceraEnArray = getNextData().split(",");
        cabeceraEnArray[cabeceraEnArray.length-1] = "class-number";
        datos = new TableWithLabels(cabeceraEnArray);
    }

    @Override
    void processData(String data) {
        String[] filaEnTexto = getNextData().split(",");
        Double[] filaEnDouble = new Double[filaEnTexto.length-1];
        for (int i = 0; i < filaEnDouble.length; i++) {
            filaEnDouble[i] = Double.parseDouble(filaEnTexto[i]);
        }
        String clase = filaEnTexto[filaEnTexto.length-1];
        ((TableWithLabels) datos).add(filaEnDouble, clase);
    }

}
