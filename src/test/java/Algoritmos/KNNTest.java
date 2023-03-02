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
        List<Double> listaPrueba1 = new ArrayList<>();
        listaPrueba1.add(5.7);
        listaPrueba1.add(3.8);
        listaPrueba1.add(1.7);
        listaPrueba1.add(0.4);

        List<Double> listaPrueba2 = new ArrayList<>();
        listaPrueba2.add(5.7);
        listaPrueba2.add(2.6);
        listaPrueba2.add(3.5);
        listaPrueba2.add(1.1);

        List<Double> listaPrueba3 = new ArrayList<>();
        listaPrueba3.add(6.3);
        listaPrueba3.add(2.7);
        listaPrueba3.add(4.9);
        listaPrueba3.add(1.7);



        assertEquals(estimador.estimate(listaPrueba1), 0);
        assertEquals(estimador.estimate(listaPrueba2), 1);
        assertEquals(estimador.estimate(listaPrueba3), 2);
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