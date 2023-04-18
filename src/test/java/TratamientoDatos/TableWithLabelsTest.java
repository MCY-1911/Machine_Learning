package TratamientoDatos;

import TratamientoDatos.Lectores.CSVLabeledFileReader;
import TratamientoDatos.Lectores.ReaderTemplate;
import TratamientoDatos.Tablas.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static TratamientoDatos.Tablas.TableWithLabels.TABLA_LABELS_NULA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableWithLabelsTest {

    ReaderTemplate constructorTabla;
    TableWithLabels tablaLabels;

    public TableWithLabelsTest() {
        constructorTabla = new CSVLabeledFileReader("src" + File.separator + "Files" + File.separator + "iris.csv");
        tablaLabels = (TableWithLabels) constructorTabla.readTableFromSource();
    }

    @Test
    void TestFicheroNoValido(){
        System.out.println("En este test comprobaremos si CSV maneja bien los ficheros no encontrados.");
        ReaderTemplate constructorTablaConEtiquetasNoExistente = new CSVLabeledFileReader("Este fichero no existe");
        TableWithLabels tablaConEtiquetasNoExistente = (TableWithLabels) constructorTablaConEtiquetasNoExistente.readTableFromSource();
        assertEquals(TABLA_LABELS_NULA, tablaConEtiquetasNoExistente);
    }

    @Test
    void TestNumeroFilas(){
        System.out.println("En este test comprobamos el número de filas leído");
        assertEquals(150, tablaLabels.getNumeroFilas());
    }

    @Test
    void TestNumeroColumnas(){
        System.out.println("En este test comprobamos si el número de atributos es válido");
        assertEquals(5, tablaLabels.getNumeroColumnas());
    }

    @Test
    void TestHeaders(){
        System.out.println("En este test comprobamos si las cabeceras son correctas");
        List<String> headersPruebaIris = new ArrayList<>();
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
        assertEquals(0, tablaLabels.getRowAt(1).getNumberClass());
        assertEquals(1, tablaLabels.getRowAt(53).getNumberClass());
        assertEquals(2,tablaLabels.getRowAt(119).getNumberClass());
    }

    @Test
    void TestRecuperarFilas(){
        System.out.println("Test para recuperar filas de la tabla.");

        List<Double> row0TableWithLabels = new ArrayList<>();
        List<Double> row58TableWithLabels = new ArrayList<>();
        List<Double> row126TableWithLabels = new ArrayList<>();

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

        assertEquals(row0TableWithLabels, tablaLabels.getRowAt(0).getData());
        assertEquals(row58TableWithLabels, tablaLabels.getRowAt(57).getData());
        assertEquals(row126TableWithLabels, tablaLabels.getRowAt(125).getData());
    }

}
