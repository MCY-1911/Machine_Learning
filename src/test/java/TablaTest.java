import PR1.CSV;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablaTest {


    PR1.CSV csv = new CSV();
    PR1.Table tabla1;
    PR1.Table tabla2;

    PR1.TableWithLabels tablaLabels;

    List headersPrueba1  = new ArrayList<>();
    List headersPrueba2 = new ArrayList<>();
    List rowPrueba1 = new ArrayList<>();
    List rowPrueba2 = new ArrayList<>();
    List rowPrueba3 = new ArrayList<>();




    @BeforeEach
    void setUp() throws IOException {
        tabla1 = csv.readTable("miles_dollars.csv");
        tablaLabels = csv.readTableWithLabels("iris.csv");


    }
    @Test
    void filas(){
        assertEquals(25, tabla1.getFilas());
        assertEquals(150, tablaLabels.getFilas());
    }

    @Test
    void columnas(){
        assertEquals(2, tabla1.getColumnas());
        assertEquals(5, tablaLabels.getColumnas());
    }

    @Test
    void headers(){
        headersPrueba1.add("Miles");
        headersPrueba1.add("Dollars");
        assertEquals(headersPrueba1, tabla1.getHeaders());

        headersPrueba2.add("sepal length");
        headersPrueba2.add("sepal width");
        headersPrueba2.add("petal length");
        headersPrueba2.add("petal length");
        headersPrueba2.add("petal width");
        headersPrueba2.add("class");

        assertEquals(headersPrueba2, tablaLabels.getHeaders());



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

    assertEquals(rowPrueba1, tablaLabels.getRowAt(0).getData());
    assertEquals(rowPrueba2, tablaLabels.getRowAt(58).getData());
    assertEquals(rowPrueba3, tablaLabels.getRowAt(126).getData());

    }
}
