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
    // Con esta semilla los puntos el centroide del grupo de puntos del 3º Cuadrante corresponde al 1
    Kmeans estimador2Clusters = new Kmeans(2, 10, 4l);
    Kmeans estimador28Clusters = new Kmeans(28,10, 4);
    List<Double> punto1rCuadrante = new ArrayList();
    List<Double> punto3rCuadrante = new ArrayList();

    Table TablaGruposPrimerYTercerCuadrante;

    @BeforeEach
    void setUp() throws MasDatosQueGruposException {
        TablaGruposPrimerYTercerCuadrante = constructorTablas.readTable("src" + File.separator + "Files" +File.separator + "PruebaKmeans.csv");
        estimador2Clusters.train(TablaGruposPrimerYTercerCuadrante);

    }


    @Test
    void estimatePunto1ºCuadrante()  {
        punto1rCuadrante.add(20.0);
        punto1rCuadrante.add(22.0);
        assertEquals(2, estimador2Clusters.estimate(punto1rCuadrante));
    }

    @Test
    void estimatePunto3ºCuadrante()  {
        punto3rCuadrante.add(-7.0);
        punto3rCuadrante.add(-7.0);
        assertEquals(1, estimador2Clusters.estimate(punto3rCuadrante));
    }

    @Test
    void TestExcepción() throws MasDatosQueGruposException {
        assertThrows(MasDatosQueGruposException.class, () -> estimador28Clusters.train(TablaGruposPrimerYTercerCuadrante));
    }
}