package TratamientoDatos;

import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import TratamientoDatos.Tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static TratamientoDatos.Tablas.Table.TABLA_NULA;
import static TratamientoDatos.Tablas.TableWithLabels.TABLA_LABELS_NULA;


public class TablesPrints {
    public static void main(String[] args) {
        CSV creadorTablas = new CSV();
        Table tabla = creadorTablas.readTable("src/Files/miles_dollars.csv");
        TableWithLabels tablaConEtiquetas = creadorTablas.readTableWithLabels("src/Files/iris.csv");
        System.out.println("La tabla nula:\n" + TABLA_NULA);
        System.out.println("La tabla nula con etiquetas:\n" + TABLA_LABELS_NULA);
        System.out.println("La tabla de miles_dollars.csv:\n" + tabla);
        System.out.println("La tabla de iris.csv:\n" + tablaConEtiquetas);

        Scanner sc;
        try {
            sc = new Scanner(new File("src/Files/miles_dollars.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

    }
}
