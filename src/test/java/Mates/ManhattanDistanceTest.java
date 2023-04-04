package Mates;

import Exceptions.PuntosDiferentesDimensiones;
import Interfaces.Distance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {

    Distance calculadora;
    List<Double> origen;
    List<Double> puntoA;
    List<Double> puntoB;
    List<Double> punto3dimensiones;

    public ManhattanDistanceTest(){
        calculadora = new ManhattanDistance();
        origen = new ArrayList<>(2);
        origen.add(0.0);
        origen.add(0.0);
        puntoA = new ArrayList<>(2);
        puntoA.add(3.0);
        puntoA.add(0.0);
        puntoB = new ArrayList<>(2);
        puntoB.add(3.0);
        puntoB.add(4.0);
        punto3dimensiones = new ArrayList<>(3);
        punto3dimensiones.add(1.0);
        punto3dimensiones.add(1.0);
        punto3dimensiones.add(1.0);
    }

    @Test
    void calculateDistance() throws PuntosDiferentesDimensiones {
        assertEquals(3, calculadora.calculateDistance(origen, puntoA));
        assertEquals(7, calculadora.calculateDistance(origen, puntoB));
    }

    @Test
    void TestException() {
        assertThrows(PuntosDiferentesDimensiones.class, () -> calculadora.calculateDistance(origen, punto3dimensiones));
    }
}