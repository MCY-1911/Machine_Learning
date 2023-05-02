package mvc.modelo;

import algoritmos.*;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import mvc.vista.Vista;
import tratamientoDatos.lectores.*;
import tratamientoDatos.tablas.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloCanciones implements Modelo {

    // SE PUEDE REFACTORIZAR EN UN MAPA POR SI AÑADIMOS DISTANCIAS/ALGORITMOS
    private Vista implemntaciónVista;
    List<String> songs;
    private RecSys recSys;



    public ModeloCanciones() throws IOException, MasDatosQueGruposException {

        // Ruta genérica a los conjuntos de datos
        String sep = System.getProperty("file.separator");
        String ruta = "src" + sep + "files" + sep + "songs_files";

        // Nos guardamos una lista para los titulos de las canciones
        songs = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        //En un mapa guardamos el nombre del algoritmo/conjunto y su ruta correspondientee
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

        // En otro mapa guardamos los algoritmos
        Map<String, Algorithm> algorithmsWithDistance = new HashMap<>();
        algorithmsWithDistance.put("knn" + "euclidean",new KNN(new EuclideanDistance()));
        algorithmsWithDistance.put("knn" + "manhattan",new KNN(new ManhattanDistance()));
        algorithmsWithDistance.put("kmeans" + "euclidean",new Kmeans(15, 200, 4321, new EuclideanDistance()));
        algorithmsWithDistance.put("kmeans" + "manhattan",new Kmeans(15, 200, 4321, new ManhattanDistance()));

        // Tablas de datos, tanto entrenamiento como test
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        for (String stage : stages) {
            tables.put("knn" + stage, new CSVLabeledFileReader(filenames.get("knn" + stage)).readTableFromSource());
            tables.put("kmeans" + stage, new CSVUnlabeledFileReader(filenames.get("kmeans" + stage)).readTableFromSource());
        }
    }


    public void setImplemntaciónVista(Vista implemntaciónVista) {
        this.implemntaciónVista = implemntaciónVista;
    }

    @Override
    public List<String> getRecomendaciones(String song) {
        implemntaciónVista.getSong();
        return null;
    }

    //EMPLEAMOS UNA ENUMERACIÓN?
    @Override
    public List<String> getCanciones() {
        return songs;
    }
}
