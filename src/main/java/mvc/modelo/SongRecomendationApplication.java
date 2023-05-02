package mvc.modelo;

import algoritmos.KNN;
import algoritmos.Kmeans;
import algoritmos.MasDatosQueGruposException;
import algoritmos.RecSys;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import mvc.vista.Vista;
import tratamientoDatos.lectores.CSVLabeledFileReader;
import tratamientoDatos.lectores.CSVUnlabeledFileReader;
import tratamientoDatos.lectores.LectorSongs;
import tratamientoDatos.lectores.ReaderTemplate;
import tratamientoDatos.tablas.Table;
import tratamientoDatos.tablas.TableWithLabels;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SongRecomendationApplication implements Modelo {

    // SE PUEDE REFACTORIZAR EN UN MAPA POR SI AÑADIMOS DISTANCIAS/ALGORITMOS
    private Vista implemntaciónVista;
    List<String> songs;
    private RecSys recSys;
    RecSys recomendadorKnnEuclidean;
    RecSys recomendadorKnnManhattan;
    RecSys recomendadorKmeansEuclidean;
    RecSys recomendadorKmeansManhattan;


    public SongRecomendationApplication() throws IOException, MasDatosQueGruposException {

        recomendadorKnnEuclidean = new RecSys(new KNN(new EuclideanDistance()));
        recomendadorKnnManhattan = new RecSys(new KNN(new ManhattanDistance()));
        recomendadorKmeansEuclidean = new RecSys(new Kmeans(15,200, 4321, new EuclideanDistance()));
        recomendadorKmeansManhattan = new RecSys(new Kmeans(15,200, 4321, new ManhattanDistance()));

        // Ruta genérica a los conjuntos de datos
        String sep = System.getProperty("file.separator");
        String ruta = "src" + sep + "files" + sep +"songs_files";

        // Nos guardamos una lista para los titulos de las canciones
        songs = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        // Entrenamos los recomendadores
        ReaderTemplate constructorTablaTrainKnn = new CSVLabeledFileReader(ruta + sep + "songs_train.csv");
        ReaderTemplate constructorTablaTestKnn = new CSVLabeledFileReader(ruta + sep + "songs_test.csv");
        TableWithLabels entrenamientoKnn = (TableWithLabels) constructorTablaTrainKnn.readTableFromSource();
        recomendadorKnnEuclidean.train(entrenamientoKnn);
        recomendadorKnnManhattan.train(entrenamientoKnn);
        TableWithLabels testKnn = (TableWithLabels) constructorTablaTestKnn.readTableFromSource();
        recomendadorKnnEuclidean.run(testKnn, songs);
        recomendadorKnnManhattan.run(testKnn, songs);

        ReaderTemplate constructorTablaTrainKmeans = new CSVUnlabeledFileReader(ruta + sep + "songs_train_withoutnames.csv");
        ReaderTemplate constructorTablaTestKmeans = new CSVUnlabeledFileReader(ruta + sep + "songs_test_withoutnames.csv");
        Table entrenamientoKmeans = constructorTablaTrainKmeans.readTableFromSource();
        recomendadorKmeansEuclidean.train(entrenamientoKmeans);
        recomendadorKmeansManhattan.train(entrenamientoKmeans);
        Table testKmeans = constructorTablaTestKmeans.readTableFromSource();
        recomendadorKmeansEuclidean.run(testKmeans, songs);
        recomendadorKmeansManhattan.run(testKmeans, songs);
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
