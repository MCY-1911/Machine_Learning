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
    Kmeans estimador1 = new Kmeans(2, 10, 4l);
    Kmeans estimador2 = new Kmeans(28,10, 4);

    Table grupo1;

    @BeforeEach
    void setUp() throws MasDatosQueGruposException {
        grupo1 = constructorTablas.readTable("src" + File.separator + "Files" + File.separator + "PruebaKmeans.csv");
        estimador1.train(grupo1);

    }


    @Test
    void estimate() throws MasDatosQueGruposException {

        List<Double> list1 = new ArrayList();
        List<Double> list2 = new ArrayList();
        list1.add(0.0);
        list2.add(20.0);

        assertEquals(1, estimador1.estimate(list1));
        assertNotEquals(2, estimador1.estimate(list1));

        assertEquals(2, estimador1.estimate(list2));
        assertNotEquals(1, estimador1.estimate(list2));
        assertThrows(MasDatosQueGruposException.class, () -> estimador2.train(grupo1));



    }
}