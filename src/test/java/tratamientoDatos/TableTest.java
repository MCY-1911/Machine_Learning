package tratamientoDatos;

import tratamientoDatos.lectores.CSVUnlabeledFileReader;
import tratamientoDatos.lectores.ReaderTemplate;
import tratamientoDatos.tablas.Table;
import static tratamientoDatos.tablas.Table.TABLA_NULA;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TableTest {
    ReaderTemplate constructorTabla;
    Table tabla;

    public TableTest(){
        constructorTabla = new CSVUnlabeledFileReader("src" + File.separator + "files" + File.separator + "miles_dollars.csv");
        tabla = constructorTabla.readTableFromSource();
    }

    @Test
    void TestFicheroNoValido(){
        System.out.println("En este test comprobaremos si CSV maneja bien los ficheros no encontrados.");
        ReaderTemplate constructorTablaNoExistente = new CSVUnlabeledFileReader("Este fichero no existe");
        Table tablaNoExistente = constructorTablaNoExistente.readTableFromSource();
        assertEquals(TABLA_NULA, tablaNoExistente);
    }

    @Test
    void TestNumeroFilas(){
        System.out.println("En este test comprobamos el número de filas leído");
        assertEquals(25, tabla.getNumeroFilas());
    }

    @Test
    void TestNumeroColumnas(){
        System.out.println("En este test comprobamos si el número de atributos es válido");
        assertEquals(2, tabla.getNumeroColumnas());
    }

    @Test
    void TestHeaders() {
        System.out.println("En este test comprobamos si las cabeceras son correctas");
        List<String> headersPruebaMilesDollars  = new ArrayList<>();
        headersPruebaMilesDollars.add("Miles");
        headersPruebaMilesDollars.add("Dollars");
        System.out.println("Headers esperados: " + headersPruebaMilesDollars);
        System.out.println("Headers obtenidos: " + tabla.getHeaders());
        assertEquals(headersPruebaMilesDollars, tabla.getHeaders());
    }

    @Test
    void TestRecuperarFilas(){
        System.out.println("Test para recuperar filas de la tabla.");

        List<Double> row3Table = new ArrayList<>();
        row3Table.add(1687.0);
        row3Table.add(2511.0);

        List<Double> row15Table = new ArrayList<>();
        row15Table.add(3643.0);
        row15Table.add(5298.0);

        assertEquals(row3Table, tabla.getRowAt(3).getData());
        assertEquals(row15Table, tabla.getRowAt(15).getData());
    }
}
