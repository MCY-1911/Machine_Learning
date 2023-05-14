package algoritmos.aplicaciones;

import algoritmos.*;
import mates.Distance;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import tratamientoDatos.lectores.CSV;
import tratamientoDatos.lectores.LectorSongs;
import tratamientoDatos.tablas.Table;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SongRecSys {
    private RecSys recsys;

    SongRecSys(String method, String distance) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "src" + File.separator + "Files" +File.separator +"songs_files"+File.separator;

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

        // Distances
        Map<String, Distance> distances = new HashMap<>();
        distances.put("euclidean", new EuclideanDistance());
        distances.put("manhattan", new ManhattanDistance());

        // Algorithms
        // La distancia Euclidiana es un placeholder
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN(new EuclideanDistance()));
        algorithms.put("kmeans",new Kmeans(15, 200, 4321, new EuclideanDistance()));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }

        // Names of items
        List<String> names = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        DistanceClient algorithm = (DistanceClient) algorithms.get(method);
        algorithm.setDistance(distances.get(distance));
        this.recsys = new RecSys((Algorithm) algorithm);
        this.recsys.train(tables.get(method+"train"));
        this.recsys.run(tables.get(method+"test"), names);

        // Given a liked item, ask for a number of recomendations
        String liked_name = "JUNKY";
        List<String> recommended_items = this.recsys.recommend(liked_name,7);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)
        reportRecommendation(liked_name,recommended_items);

    }


    private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name);
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys("knn","euclidean");
        new SongRecSys("knn","manhattan");
        new SongRecSys("kmeans", "euclidean");
        new SongRecSys("kmeans", "manhattan");
    }
}