package Algoritmos;

import Interfaces.Algorithm;
import TratamientoDatos.Lectores.CSV;
import TratamientoDatos.Tablas.Table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SongRecSys {
    private RecSys recsys;

    SongRecSys(String method) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "src" + File.separator + "Files" +File.separator +"songs_files"+File.separator;

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN());
        algorithms.put("kmeans",new Kmeans(15, 200, 4321));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }

        // Names of items
        List<String> names = readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.run(tables.get(method+"test"), names);

        // Given a liked item, ask for a number of recomendations
        String liked_name = "Lootkemia";
        List<String> recommended_items = this.recsys.recommend(liked_name,7);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)



        reportRecommendation(liked_name,recommended_items);

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

    private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+ "("+ recsys.getIdentificador(liked_name)+")"+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name+"("+ recsys.getIdentificador(name) +")");
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys("knn");
        new SongRecSys("kmeans");


    }
}