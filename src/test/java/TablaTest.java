import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablaTest {

    PR1.Table tabla;

    PR1.TableWithLabels tablaLabels;

    List headersPrueba  = new ArrayList<>();
    List rowPrueba1 = new ArrayList<>();
    List rowPrueba2 = new ArrayList<>();
    List rowPrueba3 = new ArrayList<>();


    @BeforeEach
    void setUp(){

    }
    @Test
    void filas(){
        assertEquals(25, tabla.getFilas());
    }

    @Test
    void columnas(){
        assertEquals(2, tabla.getColumnas());
    }

    @Test
    void headers(){
        headersPrueba.add("Miles");
        headersPrueba.add("Dollars");
        assertEquals(headersPrueba, tabla.getHeaders());
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

    assertEquals(rowPrueba1, tabla.getRowAt(0).getData());
    assertEquals(rowPrueba2, tabla.getRowAt(58).getData());
    assertEquals(rowPrueba3, tabla.getRowAt(126).getData());

    }
}
