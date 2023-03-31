package TratamientoDatos;

import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import TratamientoDatos.Tablas.TableWithLabels;

import static TratamientoDatos.Tablas.Table.TABLA_NULA;
import static TratamientoDatos.Tablas.TableWithLabels.TABLA_LABELS_NULA;


public class Main {
    public static void main(String[] args) {
        CSV creadorTablas = new CSV();
        Table tabla = creadorTablas.readTable("src/Files/miles_dollars.csv");
        TableWithLabels tablaConEtiquetas = creadorTablas.readTableWithLabels("src/Files/iris.csv");
        System.out.println("La tabla nula:\n" + TABLA_NULA);
        System.out.println("La tabla nula con etiquetas:\n" + TABLA_LABELS_NULA);
        System.out.println("La tabla de miles_dollars.csv:\n" + tabla);
        System.out.println("La tabla de iris.csv:\n" + tablaConEtiquetas);
    }
}
