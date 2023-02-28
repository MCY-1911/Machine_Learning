package main;

import PR1.CSV;

import java.io.IOException;

import static PR1.Table.TABLA_NULA;

public class Main {
    public static void main(String[] args) throws IOException {


        PR1.CSV csv = new CSV();
        PR1.Table tabla1;
        PR1.TableWithLabels tablaLabels;

        tablaLabels = csv.readTableWithLabels("src/Files/iris.csv");

        System.out.println(tablaLabels);
    }
}
