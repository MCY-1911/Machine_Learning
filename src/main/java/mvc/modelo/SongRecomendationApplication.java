package mvc.modelo;

import algoritmos.KNN;
import algoritmos.Kmeans;
import algoritmos.RecSys;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import mvc.vista.Vista;
import tratamientoDatos.lectores.CSVLabeledFileReader;
import tratamientoDatos.lectores.LectorSongs;
import tratamientoDatos.lectores.ReaderTemplate;

import java.io.File;
import java.util.List;

public class SongRecomendationApplication implements Modelo {

    Vista implemntaciónVista;


    RecSys recomendadorKnnEuclidean = new RecSys(new KNN(new EuclideanDistance()));
    RecSys recomendadorKnnManhattan = new RecSys(new KNN(new ManhattanDistance()));
    RecSys recomendadorKmeansEuclidean = new RecSys(new Kmeans(15,200, 4321, new EuclideanDistance()));
    RecSys recomendadorKmeansManhattan = new RecSys(new Kmeans(15,200, 4321, new ManhattanDistance()));


    // Ruta genérica a los conjuntos de datos
    String sep = System.getProperty("file.separator");
    String ruta = "src" + File.separator + "Files" +File.separator +"songs_files"+File.separator;
    //List<String> songs = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

    ReaderTemplate constructorTablaTrainKnn = new CSVLabeledFileReader(ruta + sep + "songs_train.csv");
    ReaderTemplate constructorTablaTestKnn = new CSVLabeledFileReader(ruta + sep + "songs_test.csv");



    public SongRecomendationApplication() {
        super();
    }

    public void setImplemntaciónVista(Vista implemntaciónVista) {
        this.implemntaciónVista = implemntaciónVista;
    }

    @Override
    public List<String> getRecomendaciones(String song) {
        return null;
    }

    @Override
    public List<String> getCanciones() {
        return null;
    }
}
