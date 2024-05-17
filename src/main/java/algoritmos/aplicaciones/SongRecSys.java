package algoritmos.aplicaciones;

import algoritmos.*;
import mates.Distance;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import tratamientodatos.lectores.CSV;
import tratamientodatos.lectores.LectorSongs;
import tratamientodatos.tablas.Table;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SongRecSys {
    private RecSys recsys;
    private static final String TRAIN = "train";
    private static final String TEST = "test";
    private static final String KMEANS = "kmeans";
    private static final String KNN = "knn";
    private static final String EUCLIDEAN = "euclidean";
    private static final String MANHATTAN = "manhattan";

    SongRecSys(String method, String distance) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "src" + File.separator + "Files" +File.separator +"songs_files"+File.separator;

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+ TRAIN,ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put(KMEANS+ TRAIN,ruta+sep+"songs_train_withoutnames.csv");
        filenames.put(KMEANS+"test",ruta+sep+"songs_test_withoutnames.csv");

        // Distances
        Map<String, Distance> distances = new HashMap<>();
        distances.put(EUCLIDEAN, new EuclideanDistance());
        distances.put(MANHATTAN, new ManhattanDistance());

        // Algorithms
        // La distancia Euclidiana es un placeholder
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN(new EuclideanDistance()));
        algorithms.put(KMEANS,new Kmeans(15, 200, 4321, new EuclideanDistance()));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {TRAIN, "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put(KMEANS + stage, csv.readTable(filenames.get(KMEANS + stage)));
        }

        // Names of items
        List<String> names = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        DistanceClient algorithm = (DistanceClient) algorithms.get(method);
        algorithm.setDistance(distances.get(distance));
        this.recsys = new RecSys((Algorithm) algorithm);
        this.recsys.train(tables.get(method+ TRAIN));
        this.recsys.run(tables.get(method+"test"), names);

        // Given a liked item, ask for a number of recomendations
        String likedName = "JUNKY";
        List<String> recommendedItems = this.recsys.recommend(likedName,7);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)
        reportRecommendation(likedName,recommendedItems);

    }


    private void reportRecommendation(String likedName, List<String> recommendedItems) {
        System.out.println("If you liked \""+likedName+"\" then you might like:");
        for (String name : recommendedItems)
        {
            System.out.println("\t * "+name);
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys(KNN,EUCLIDEAN);
        new SongRecSys(KNN,MANHATTAN);
        new SongRecSys(KMEANS, EUCLIDEAN);
        new SongRecSys(KMEANS, MANHATTAN);
    }
}