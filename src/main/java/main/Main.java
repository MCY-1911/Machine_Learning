package main;

import  PR1.*;
import static PR1.Table.TABLA_NULA;
import static PR1.TableWithLabels.TABLA_LABELS_NULA;


public class Main {
    public static void main(String[] args) {
        CSV creadorTablas = new CSV();
        Table tabla = creadorTablas.readTable("src/Files/miles_dollars.csv");
        TableWithLabels tablaConEtiquetas = creadorTablas.readTableWithLabels("src/Files/iris.csv");
        System.out.println("La tabla nula:\n" + TABLA_NULA);
        System.out.println("La tabla nula con etiquetas:\n" + TABLA_LABELS_NULA);


    }
}
