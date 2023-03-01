import PR1.CSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static PR1.Table.TABLA_NULA;
import static PR1.TableWithLabels.TABLA_LABELS_NULA;
import static org.junit.jupiter.api.Assertions.assertEquals;

import PR1.*;
public class TablaTest {


    PR1.CSV constructorTablas = new CSV();
    Table tabla;
    TableWithLabels tablaLabels;

    Table tablaNoExistente;
    TableWithLabels tablaConEtiquetasNoExistente;

    List headersPruebaMilesDollars  = new ArrayList<>();
    List headersPruebaIris = new ArrayList<>();
    List row0TableWithLabels = new ArrayList<>();
    List row58TableWithLabels = new ArrayList<>();
    List row126TableWithLabels = new ArrayList<>();
    List row3Table = new ArrayList<>();
    List row15Table = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        tabla = constructorTablas.readTable("src" + File.separator + "Files" + File.separator + "miles_dollars.csv");
        tablaLabels = constructorTablas.readTableWithLabels("src" + File.separator + "Files" + File.separator + "iris.csv");
        tablaNoExistente = constructorTablas.readTable("Este fichero no existe");
        tablaConEtiquetasNoExistente = constructorTablas.readTableWithLabels("Este fichero no existe");

    }
    @Test
    void TestFicheroNoValido(){
        System.out.println("En este test comprobaremos si CSV maneja bien los ficheros no encontrados.");
        assertEquals(TABLA_NULA, tablaNoExistente);
        assertEquals(TABLA_LABELS_NULA, tablaConEtiquetasNoExistente);
    }

    @Test
    void TestNumeroFilas(){
        System.out.println("En este test comprobamos el número de filas leído");
        assertEquals(25, tabla.getNumeroFilas());
        assertEquals(150, tablaLabels.getNumeroFilas());
    }

    @Test
    void TestNumeroColumnas(){
        System.out.println("En este test comprobamos si el número de atributos es válido");
        assertEquals(2, tabla.getNumeroColumnas());
        assertEquals(5, tablaLabels.getNumeroColumnas());
    }

    @Test
    void TestHeaders(){
        System.out.println("En este test comprobamos si las cabeceras son correctas");
        System.out.println("PRUEBA 1");
        headersPruebaMilesDollars.add("Miles");
        headersPruebaMilesDollars.add("Dollars");
        System.out.println("Headers esperados: " + headersPruebaMilesDollars);
        System.out.println("Headers obtenidos: " + tabla.getHeaders());
        assertEquals(headersPruebaMilesDollars, tabla.getHeaders());

        System.out.println("\nPRUEBA 2");
        headersPruebaIris.add("sepal length");
        headersPruebaIris.add("sepal width");
        headersPruebaIris.add("petal length");
        headersPruebaIris.add("petal width");
        headersPruebaIris.add("class-number");
        System.out.println("Headers esperados: " + headersPruebaIris);
        System.out.println("Headers obtenidos: " + tablaLabels.getHeaders());
        assertEquals(headersPruebaIris, tablaLabels.getHeaders());



    }

    @Test
    void TestObtenerNumeroClase(){
        System.out.println("En este test comprobaremos la asignación de los números de clase");
        assertEquals(0, tablaLabels.getRowAt(1).getNumbreClass());
        assertEquals(1, tablaLabels.getRowAt(53).getNumbreClass());
        assertEquals(2,tablaLabels.getRowAt(119).getNumbreClass());

    }

    @Test
    void TestRecuperarFilas(){
        System.out.println("Test para recuperar filas de la tabla.");

        row0TableWithLabels.add(5.1);
        row0TableWithLabels.add(3.5);
        row0TableWithLabels.add(1.4);
        row0TableWithLabels.add(0.2);

        row58TableWithLabels.add(4.9);
        row58TableWithLabels.add(2.4);
        row58TableWithLabels.add(3.3);
        row58TableWithLabels.add(1.0);

        row126TableWithLabels.add(7.2);
        row126TableWithLabels.add(3.2);
        row126TableWithLabels.add(6.0);
        row126TableWithLabels.add(1.8);

        row3Table.add(1687.0);
        row3Table.add(2511.0);

        row15Table.add(3643.0);
        row15Table.add(5298.0);

    assertEquals(row0TableWithLabels, tablaLabels.getRowAt(0).getData());
    assertEquals(row58TableWithLabels, tablaLabels.getRowAt(57).getData());
    assertEquals(row126TableWithLabels, tablaLabels.getRowAt(125).getData());
    assertEquals(row3Table, tabla.getRowAt(3).getData());
    assertEquals(row15Table, tabla.getRowAt(15).getData());


    }
}
