package TratamientoDatos.Lectores;

import TratamientoDatos.Tablas.Table;

public class CSVUnlabeledFileReader extends ReaderTemplate{
    @Override
    void createTable() {
        datos = new Table();
    }

    @Override
    void openSource(String source) {
        try {

        }
    }

    @Override
    void processHeaders(String headers) {

    }

    @Override
    void processData(String data) {

    }

    @Override
    void closeSource() {

    }

    @Override
    boolean hasMoreData() {
        return false;
    }

    @Override
    String getNextData() {
        return null;
    }
}
