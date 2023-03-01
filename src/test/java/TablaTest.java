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

    List headersPrueba1  = new ArrayList<>();
    List headersPrueba2 = new ArrayList<>();
    List rowPrueba1 = new ArrayList<>();
    List rowPrueba2 = new ArrayList<>();
    List rowPrueba3 = new ArrayList<>();
    List rowPrueba4 = new ArrayList<>();
    List rowPrueba5 = new ArrayList<>();


    @BeforeEach
    void setUp() throws IOException {
        System.out.println("Creamos dos tablas de pruebas.");
        System.out.println("Tabla sin etiquetas (miles_dollars.csv):");
        tabla = constructorTablas.readTable("src" + File.separator + "Files" + File.separator + "miles_dollars.csv");
        System.out.println(tabla);
        System.out.println("Tabla sin etiquetas (iris.csv):");
        tablaLabels = constructorTablas.readTableWithLabels("src" + File.separator + "Files" + File.separator + "iris.csv");
        System.out.println(tablaLabels);
        System.out.println("Y una tabla de cada tipo a partir de un fichero inexistente.");
        tablaNoExistente = constructorTablas.readTable("Este fichero no existe");
        tablaConEtiquetasNoExistente = constructorTablas.readTableWithLabels("Este fichero no existe");

    }
    @Test
    void TestFicheroNoValido(){
        assertEquals(TABLA_NULA, tablaNoExistente);
        assertEquals(TABLA_LABELS_NULA, tablaConEtiquetasNoExistente);
    }

    @Test
    void TestNumeroFilas(){
        assertEquals(25, tabla.getNumeroFilas());
        assertEquals(150, tablaLabels.getNumeroFilas());
    }

    @Test
    void TestNumeroColumnas(){
        assertEquals(2, tabla.getNumeroColumnas());
        assertEquals(6, tablaLabels.getNumeroColumnas());
    }

    @Test
    void headers(){
        headersPrueba1.add("Miles");
        headersPrueba1.add("Dollars");
        assertEquals(headersPrueba1, tabla.getHeaders());

        headersPrueba2.add("sepal length");
        headersPrueba2.add("sepal width");
        headersPrueba2.add("petal length");
        headersPrueba2.add("petal length");
        headersPrueba2.add("petal width");
        headersPrueba2.add("class");

      //  assertEquals(headersPrueba2, tablaLabels.getHeaders());



    }

    @Test
    void numFila(){

        assertEquals(0, tablaLabels.getRowAt(1).getNumbreClass());
        assertEquals(1, tablaLabels.getRowAt(53).getNumbreClass());
        assertEquals(2,tablaLabels.getRowAt(119).getNumbreClass());

    }

    @Test
    void datosFila(){
//0
        rowPrueba1.add(5.1);
        rowPrueba1.add(3.5);
        rowPrueba1.add(1.4);
        rowPrueba1.add(0.2);
//58
        rowPrueba2.add(4.9);
        rowPrueba2.add(2.4);
        rowPrueba2.add(3.3);
        rowPrueba2.add(1.0);
//126
        rowPrueba3.add(7.2);
        rowPrueba3.add(3.2);
        rowPrueba3.add(6.0);
        rowPrueba3.add(1.8);

        rowPrueba4.add(1687.0);
        rowPrueba4.add(2511.0);

        rowPrueba5.add(3643.0);
        rowPrueba5.add(5298.0);



    assertEquals(rowPrueba1, tablaLabels.getRowAt(0).getData());
    assertEquals(rowPrueba2, tablaLabels.getRowAt(57).getData());
    assertEquals(rowPrueba3, tablaLabels.getRowAt(125).getData());
    assertEquals(rowPrueba4, tabla.getRowAt(3).getData());
    assertEquals(rowPrueba5, tabla.getRowAt(15).getData());


    }
}
