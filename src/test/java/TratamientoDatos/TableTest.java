package TratamientoDatos;

import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import static TratamientoDatos.Tablas.Table.TABLA_NULA;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TableTest {
    CSV constructorTabla;
    Table tabla;
    Table tablaNoExistente;
    List headersPruebaMilesDollars;
    List row3Table;
    List row15Table;

    public TableTest(){
        constructorTabla = new CSV();
        tabla = constructorTabla.readTable("src" + File.separator + "Files" + File.separator + "miles_dollars.csv");
        tablaNoExistente = constructorTabla.readTable("Este fichero no existe");
        headersPruebaMilesDollars  = new ArrayList<>();
        row3Table = new ArrayList<>();
        row15Table = new ArrayList<>();
    }

    @Test
    void TestFicheroNoValido(){
        System.out.println("En este test comprobaremos si CSV maneja bien los ficheros no encontrados.");
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
        headersPruebaMilesDollars.add("Miles");
        headersPruebaMilesDollars.add("Dollars");
        System.out.println("Headers esperados: " + headersPruebaMilesDollars);
        System.out.println("Headers obtenidos: " + tabla.getHeaders());
        assertEquals(headersPruebaMilesDollars, tabla.getHeaders());
    }

    @Test
    void TestRecuperarFilas(){
        System.out.println("Test para recuperar filas de la tabla.");

        row3Table.add(1687.0);
        row3Table.add(2511.0);

        row15Table.add(3643.0);
        row15Table.add(5298.0);

        assertEquals(row3Table, tabla.getRowAt(3).getData());
        assertEquals(row15Table, tabla.getRowAt(15).getData());
    }
}
