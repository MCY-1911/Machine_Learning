package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import Mates.EuclideanDistance;
import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {

    RecSys recsysKnn = new RecSys(new KNN(new EuclideanDistance()));
    RecSys recsysKmeans = new RecSys(new Kmeans(15, 200, 4321, new EuclideanDistance()));
    Map<String, String> filenames = new HashMap<>();
    Map<String, Table> tables = new HashMap<>();
    List<String> names;
    List<String> listKnn = new ArrayList<>();
    List<String> listKmeans = new ArrayList<>();



    public RecSysTest() throws IOException, MasDatosQueGruposException {

        // Creamos la ruta genérica a los conjuntos de train y test
        String sep = System.getProperty("file.separator");
        String ruta = "src" + File.separator + "Files" + File.separator + "songs_files" + File.separator;

        // Guardamos en un mapa las rutas específicas
        filenames.put("knn" + "train", ruta + sep + "songs_train.csv");
        filenames.put("knn" + "test", ruta + sep + "songs_test.csv");
        filenames.put("kmeans" + "train", ruta + sep + "songs_train_withoutnames.csv");
        filenames.put("kmeans" + "test", ruta + sep + "songs_test_withoutnames.csv");

        // Creamos las tablas
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }

        // Obtenemos los titulos de las canciones
        names = readNames(ruta+sep+"songs_test_names.csv");

        // Iniciamos los sistemas de recomendacion. Entrenar + iniciar
        this.recsysKnn.train(tables.get("knn"+"train"));
        this.recsysKnn.run(tables.get("knn"+"test"), names);
        this.recsysKmeans.train(tables.get("kmeans"+"train"));
        this.recsysKmeans.run(tables.get("kmeans"+"test"), names);

        listKnn.add("Headbangers:Dubstep/Riddim");
        listKnn.add("JUNKY");
        listKnn.add("Psytrance: From Full on to Forrest Trance");
        listKnn.add("About the Money (feat. Young Thug)");
        listKnn.add("Psytrance: From Full on to Forrest Trance");


        listKmeans.add("Gangsta Gangsta");
        listKmeans.add("Guilty Conscience");
        listKmeans.add("Dirtybird Players");
        listKmeans.add("Melly the Menace");
        listKmeans.add("Bang Your Line (feat. Ty Dolla $ign)");

    }



    @Test
    void recommendTest() {
        // Para comprobar que la recomendación es correcta simplemente utilizaremos un método auxiliar que nos devuelva
        // un conjunto con todos los titulos de un grupo determinado, cogeremos algunos de estos y pediremos recomendaciones
        // si dichas recomendaciones pertenecen al conjunto del grupo. La recomendación la damos por buena.
        // PUEDE FALLAR SI ESCOGE UNA CANCIÓN QUE PERTENECE A DIFERENTES GRUPOS (por qué su título se repite)
        System.out.print("Este test crea un conjunto con todos los títulos que forman parte de un grupo,\n" +
                "escoge uno para buscar recomendaciones y comprueba si las recomendaciones son del grupo correcto.\n");
        Set<String> titulosMismoGrupoKNN = recsysKnn.devuelveCancionesConMismoGrupo(0);
        Set<String> titulosMismoGrupoKmeans = recsysKmeans.devuelveCancionesConMismoGrupo(14);

        Iterator<String> it = titulosMismoGrupoKNN.iterator();
        List<String> recomendacionesKnn = recsysKnn.recommend(it.next(), 5);
        assertTrue(titulosMismoGrupoKNN.containsAll(recomendacionesKnn));
        it = titulosMismoGrupoKmeans.iterator();
        List<String> recomendacionesKmeans = recsysKmeans.recommend(it.next(), 5);
        assertTrue(titulosMismoGrupoKmeans.containsAll(recomendacionesKmeans));
    }

    private List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }

}