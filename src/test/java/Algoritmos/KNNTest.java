package Algoritmos;

import PR1.CSV;
import PR1.RowWithLabel;
import PR1.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {

    TableWithLabels tablaLabels;
    CSV constructorTablas = new CSV();
    KNN estimador = new KNN();
    KNN estimadorSinMuestra = new KNN();
    @BeforeEach
    void setUp() {
        tablaLabels = constructorTablas.readTableWithLabels("src" + File.separator + "Files" + File.separator + "iris.csv");
        estimador.train(tablaLabels);
    }

    @Test
    void estimate() {
        
    }

    @Test
    void getDistancia() {
        System.out.println("Este método comprueba que él cálculo de la distancia es correcto");

        Double[] atributosIguales = {5.5, 5.5, 5.5};
        List<Double> listaAtributosIguales = new ArrayList<>();
        listaAtributosIguales.add(5.5);
        listaAtributosIguales.add(5.5);
        listaAtributosIguales.add(5.5);
        RowWithLabel RowTodosIguales = new RowWithLabel(atributosIguales, -1);
        assertEquals(estimadorSinMuestra.getDistancia(listaAtributosIguales, RowTodosIguales), 0.0);

        Double[] atributosPrueba = {0.0, 4.0, 3.0};
        List<Double> listaEjemplar1 = new ArrayList<>();
        listaEjemplar1.add(0.0);
        listaEjemplar1.add(0.0);
        listaEjemplar1.add(0.0);
        RowWithLabel RowCandidato = new RowWithLabel(atributosPrueba, -1);
        assertEquals(estimadorSinMuestra.getDistancia(listaEjemplar1, RowCandidato), 5.0);

    }
}