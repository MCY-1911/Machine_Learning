package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import Mates.EuclideanDistance;
import TratamientoDatos.Filas.Row;
import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {

    CSV constructorTablas = new CSV();
    // Con esta semilla los puntos el centroide del grupo de puntos del 3º Cuadrante corresponde al 1
    Kmeans estimador2Clusters = new Kmeans(2, 20, 4, new EuclideanDistance());
    Kmeans estimador28Clusters = new Kmeans(28,10, 4, new EuclideanDistance());


    Table TablaGruposPrimerYTercerCuadrante;


     public KmeansTest() throws MasDatosQueGruposException {
        TablaGruposPrimerYTercerCuadrante = constructorTablas.readTable("src" + File.separator + "Files" +File.separator + "PruebaKmeans.csv");
        estimador2Clusters.train(TablaGruposPrimerYTercerCuadrante);
         try {
             saveTableWithPredictions(TablaGruposPrimerYTercerCuadrante,  "src" + File.separator + "Files" +File.separator + "PruebaKmeans"+ "_out.csv", estimador2Clusters);
         } catch (IOException e) {
             e.printStackTrace();
         }

    }


    @Test
    void estimatePunto1rCuadrante()  {
        List<Double> punto1rCuadrante = new ArrayList<>();
        punto1rCuadrante.add(20.0);
        punto1rCuadrante.add(22.0);
        assertEquals(2, estimador2Clusters.estimate(punto1rCuadrante));
    }

    @Test
    void estimatePunto3rCuadrante()  {
        List<Double> punto3rCuadrante = new ArrayList<>();
        punto3rCuadrante.add(-7.0);
        punto3rCuadrante.add(-7.0);
        assertEquals(1, estimador2Clusters.estimate(punto3rCuadrante));
    }

    @Test
    void estimatePuntosDiferentesCuadrantes() {
        List<Double> punto1rCuadrante = new ArrayList<>();
        punto1rCuadrante.add(20.0);
        punto1rCuadrante.add(22.0);
        List<Double> punto3rCuadrante = new ArrayList<>();
        punto3rCuadrante.add(-7.0);
        punto3rCuadrante.add(-7.0);
        assertNotEquals(estimador2Clusters.estimate(punto1rCuadrante), estimador2Clusters.estimate(punto3rCuadrante));

    }

    @Test
    void TestExcepcion(){
        assertThrows(MasDatosQueGruposException.class, () -> estimador28Clusters.train(TablaGruposPrimerYTercerCuadrante));
    }

    public void saveTableWithPredictions(Table t, String filename, Kmeans estimador) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.getNumeroFilas(); i++)
            {
                Row row = t.getRowAt(i);
                List<Double> datos = row.getData();
                datos.add((double)estimador.estimate(datos));
                int j=0;
                for (; j<datos.size()-1; j++)
                {
                    fw.write(datos.get(j).toString());
                    fw.write(",");
                }
                fw.write(datos.get(j).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

}