package TratamientoDatos.Lectores;


import TratamientoDatos.Tablas.Table;
import TratamientoDatos.Tablas.TableWithLabels;

import java.io.*;

public class CSV {

    public CSV(){
        super();
    }

    public Table readTable(String nombreFichero) {
        //Método para leer datos de fichero CSV que no contienen el atributo "label"
        //Para realizar la lectura del fichero emplearemos la clase BufferedReader, ya que es más eficiente
        //gracias a su buffer. Este buffer permite reducir los accesos al disco.

        try( BufferedReader lector = new BufferedReader(new FileReader(nombreFichero)) ) {

            // Creamos una tabla con cabecera igual a la primera fila del CSV
            String[] cabeceraEnArray = lector.readLine().split(",");
            Table tabla = new Table(cabeceraEnArray);

            // Leemos el resto del fichero línea a línea e insertamos en la tabla
            String lineaActual;
            while ((lineaActual = lector.readLine()) != null) {
                String[] filaEnTexto = lineaActual.split(",");
                Double[] filaEnDouble = new Double[filaEnTexto.length];
                int indice = 0;
                for (String dato : filaEnTexto)
                    filaEnDouble[indice++] = Double.parseDouble(dato);
                tabla.add(filaEnDouble);
            }

            // Una vez construida la tabla la devolvemos
            return tabla;

        } catch (IOException e) {
            return Table.TABLA_NULA;
        }

    }

    public TableWithLabels readTableWithLabels(String nombreFichero) {

        try( BufferedReader lector = new BufferedReader(new FileReader(nombreFichero)) ) {

            // Creamos una tabla con etiquetas empleando como cabecera la primera fila del CSV
            String[] cabeceraEnArray = lector.readLine().split(",");
            cabeceraEnArray[cabeceraEnArray.length-1] = "class-number";
            TableWithLabels tablaConEtiquetas = new TableWithLabels(cabeceraEnArray);

            // Leemos el resto del fichero línea a línea e insertamos en la tabla con etiquetas
            String lineaActual;
            while ((lineaActual = lector.readLine()) != null) {
                String[] filaEnTexto = lineaActual.split(",");
                Double[] filaEnDouble = new Double[filaEnTexto.length-1];
                for (int i = 0; i < filaEnDouble.length; i++) {
                    filaEnDouble[i] = Double.parseDouble(filaEnTexto[i]);
                }
                String clase = filaEnTexto[filaEnTexto.length-1];
                tablaConEtiquetas.add(filaEnDouble, clase);
            }
            return tablaConEtiquetas;

        } catch (IOException e) {
            return TableWithLabels.TABLA_LABELS_NULA;
        }
    }

}
