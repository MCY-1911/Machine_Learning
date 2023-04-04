package Mates;

import Interfaces.Distance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {

    Distance calculadora;
    List<Double> origen;
    List<Double> puntoA;
    List<Double> puntoB;

    public EuclideanDistanceTest(){
        calculadora = new EuclideanDistance();
        origen = new ArrayList<>(2);
        origen.add(0.0);
        origen.add(0.0);
        puntoA = new ArrayList<>(2);
        puntoA.add(3.0);
        puntoA.add(0.0);
        puntoB = new ArrayList<>(2);
        puntoB.add(3.0);
        puntoB.add(4.0);
    }

    @Test
    void calculateDistance() {
        assertEquals(3, calculadora.calculateDistance(origen, puntoA));
        assertEquals(5, calculadora.calculateDistance(origen, puntoB));
    }
}