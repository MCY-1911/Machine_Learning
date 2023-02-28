package PR1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

    public CSV(){
        super();
    }

    public Table readTable(String nombreFichero) throws IOException {
        //Método para leer datos de fichero CSV que no contienen el atributo "label"
        //Para realizar la lectura del fichero emplearemos la clase BufferedReader, ya que es más eficiente
        //gracias a su buffer. Este buffer permite reducir los accesos al disco.

        if (comprobarFichero(nombreFichero) == false) {
            return Table.TABLA_NULA;
        }

        BufferedReader lector = new BufferedReader(new FileReader(nombreFichero));
        String[] cabeceraVec = lector.readLine().split(",");
        Table tabla = new Table(cabeceraVec);

        String lineaActual;
        while ((lineaActual = lector.readLine()) != null) {
            String[] filaEnTexto = lineaActual.split(",");
            Double[] filaEnDouble = new Double[filaEnTexto.length];
            int indice = 0;
            for (String dato: filaEnTexto)
                filaEnDouble[indice++] = Double.parseDouble(dato);
            tabla.add(filaEnDouble);
        }
        return tabla;
    }

    public TableWithLabels readTableWithLabels(String nombreFichero) throws IOException {

        if (comprobarFichero(nombreFichero) == false) {
            return TableWithLabels.TABLA_LABELS_NULA;
        }

        BufferedReader lector = new BufferedReader(new FileReader(nombreFichero));
        String[] cabeceraVec = lector.readLine().split(",");
        TableWithLabels tablaConEtiquetas = new TableWithLabels(cabeceraVec);

        String lineaActual;
        while ((lineaActual = lector.readLine()) != null) {
            String[] filaEnTexto = lineaActual.split(",");
            Double[] filaEnDouble = new Double[filaEnTexto.length];
            int indice = 0;
            for (String dato: filaEnTexto)
                filaEnDouble[indice++] = Double.parseDouble(dato);
            tablaConEtiquetas.add(filaEnDouble);
        }
        return tablaConEtiquetas;
    }

    private boolean comprobarFichero(String nombreFichero) {
        //Método auxiliar que comprueba si un fichero existe o no.
        //False si no existe, true si existe
        FileReader fichero;
        try {
            fichero = new FileReader(nombreFichero);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

}
