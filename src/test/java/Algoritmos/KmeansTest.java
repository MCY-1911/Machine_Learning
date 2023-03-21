package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import PR1.CSV;
import PR1.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {

    CSV constructorTablas = new CSV();
    Kmeans estimador = new Kmeans(2,10,12l);
    Table grupo1;
    @BeforeEach
    void setUp() throws MasDatosQueGruposException {
        grupo1 = constructorTablas.readTable("src" + File.separator + "Files" + File.separator + "PruebaKmeans.csv");
        System.out.println("he llegado1");
        System.out.println(estimador);
        estimador.train(grupo1);
        System.out.println("he llegado2");
    }


    @Test
    void estimate() {

        List<Double> list1 = new ArrayList();
        list1.add(0.0);
        assertEquals(1, estimador.estimate(list1));


    }
}