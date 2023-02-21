package PR1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

    public Table readTable(String nombre) throws IOException {
        BufferedReader objReader = null;
        String strCurrentLine;
        objReader = new BufferedReader(new FileReader(nombre));
        String[] cabeceraVec = objReader.readLine().split(",");
        Table tabla = new Table(cabeceraVec);
        while((strCurrentLine = objReader.readLine()) != null){
            tabla.add(strCurrentLine.split(","));
        }
    }

}
