package tratamientodatos;

import tratamientodatos.lectores.CSV;
import tratamientodatos.tablas.Table;
import tratamientodatos.tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

import static tratamientodatos.tablas.Table.TABLA_NULA;
import static tratamientodatos.tablas.TableWithLabels.TABLA_LABELS_NULA;


public class TablesPrints {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TablesPrints.class.getName());
        CSV creadorTablas = new CSV();
        Table tabla = creadorTablas.readTable("src/Files/miles_dollars.csv");
        TableWithLabels tablaConEtiquetas = creadorTablas.readTableWithLabels("src/Files/iris.csv");
        logger.info("La tabla nula:\n" + TABLA_NULA);
        logger.info("La tabla nula con etiquetas:\n" + TABLA_LABELS_NULA);
        logger.info("La tabla de miles_dollars.csv:\n" + tabla);
        logger.info("La tabla de iris.csv:\n" + tablaConEtiquetas);


        try(Scanner sc  = new Scanner(new File("src/Files/miles_dollars.csv"))) {
            while(sc.hasNextLine()) {
                logger.info(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
