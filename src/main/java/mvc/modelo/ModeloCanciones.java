package mvc.modelo;

import algoritmos.*;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import mvc.vista.InterrogaVista;
import tratamientoDatos.lectores.*;
import tratamientoDatos.tablas.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloCanciones implements InterrogaModelo, CambiaModelo {

    List<String> songs;
    private final Map<String, RecSys> recomendadores;
    private InterrogaVista vista;

    @Override
    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    public ModeloCanciones() throws IOException, MasDatosQueGruposException {
        // Ruta genérica a los conjuntos de datos
        String sep = System.getProperty("file.separator");
        String ruta = "src" + sep + "files" + sep + "songs_files";

        // Nos guardamos una lista para los títulos de las canciones
        songs = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        // En un mapa guardamos el nombre del algoritmo/conjunto y su ruta correspondientee
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

        // Tablas de datos, tanto entrenamiento como test
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        for (String stage : stages) {
            tables.put("knn" + stage, new CSVLabeledFileReader(filenames.get("knn" + stage)).readTableFromSource());
            tables.put("kmeans" + stage, new CSVUnlabeledFileReader(filenames.get("kmeans" + stage)).readTableFromSource());
        }

        // En otro mapa guardamos los recomendadores
        recomendadores = new HashMap<>();
        recomendadores.put("knn" + "#" + "euclidean", new RecSys(new KNN(new EuclideanDistance())));
        recomendadores.put("knn" + "#" + "manhattan",new RecSys(new KNN(new ManhattanDistance())));
        recomendadores.put("kmeans" + "#" + "euclidean",new RecSys(new Kmeans(15, 200, 4321, new EuclideanDistance())));
        recomendadores.put("kmeans" + "#" + "manhattan",new RecSys(new Kmeans(15, 200, 4321, new ManhattanDistance())));

        for(String etiqueta: recomendadores.keySet()) {
            String[] algoritmoYDistancia = etiqueta.split("#");
            recomendadores.get(etiqueta).train(tables.get(algoritmoYDistancia[0] + "train" ));
            recomendadores.get(etiqueta).run(tables.get(algoritmoYDistancia[0]+"test"), songs);
        }

    }

    @Override
    public List<String> getCanciones() {
        return songs;
    }

    @Override
    public List<String> getRecomendaciones() {
        String algorithm = vista.getAlgorithm();
        String distance = vista.getDistance();
        String song = vista.getSong();
        int numRecommendations = vista.getNumRecommendations();
        return recomendadores.get(algorithm + "#" + distance).recommend(song, numRecommendations);
    }
}
