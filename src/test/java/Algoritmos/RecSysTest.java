package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {

    RecSys recsysKnn = new RecSys(new KNN());
    RecSys recsysKmeans = new RecSys(new Kmeans(15, 200, 4321));
    Map<String, String> filenames = new HashMap<>();
    Map<String, Table> tables = new HashMap<>();

    List<String> names = new ArrayList<>();

    List<String> listKnn = new ArrayList<>();
    List<String> listKmeans = new ArrayList<>();



    public RecSysTest() throws IOException, MasDatosQueGruposException {
        String sep = System.getProperty("file.separator");
        String ruta = "src" + File.separator + "Files" + File.separator + "songs_files" + File.separator;

        // File names (could be provided as arguments to the constructor to be more general)
        filenames.put("knn" + "train", ruta + sep + "songs_train.csv");
        filenames.put("knn" + "test", ruta + sep + "songs_test.csv");
        filenames.put("kmeans" + "train", ruta + sep + "songs_train_withoutnames.csv");
        filenames.put("kmeans" + "test", ruta + sep + "songs_test_withoutnames.csv");

        // Tables
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }
        names = readNames(ruta+sep+"songs_test_names.csv");

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
    void recommend() {

        List<String> resKnn = recsysKnn.recommend("Lootkemia",5);
        List<String> resKmeans = recsysKmeans.recommend("Lootkemia",10);


        List<String> prueba = recsysKmeans.devuelveCancionesConMismoGrupo(14);

        for (String p : prueba){
            System.out.println(p);
        }

        reportRecommendationKnn("Lootkemia", resKnn);
        reportRecommendationKmeans("Lootkemia", resKmeans);

        assertEquals(resKnn, listKnn);
        assertEquals(resKmeans, listKmeans);



        for( String titulo: resKnn){
            assertEquals(recsysKnn.getIdentificador(titulo), recsysKnn.getIdentificador("Lootkemia"));
        }

        for( String titulo: resKmeans){
            assertEquals(recsysKmeans.getIdentificador(titulo), recsysKmeans.getIdentificador("Lootkemia"));
        }





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

    private void reportRecommendationKnn(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+ "("+ recsysKnn.getIdentificador(liked_name)+")"+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name+"("+ recsysKnn.getIdentificador(name) +")");
        }
    }

    private void reportRecommendationKmeans(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+ "("+ recsysKmeans.getIdentificador(liked_name)+")"+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name+"("+ recsysKmeans.getIdentificador(name) +")");
        }
    }
}