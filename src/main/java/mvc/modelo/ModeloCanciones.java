package mvc.modelo;

import algoritmos.*;
import mates.EuclideanDistance;
import mates.ManhattanDistance;
import mvc.vista.InformaVista;
import tratamientodatos.lectores.*;
import tratamientodatos.tablas.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloCanciones implements InterrogaModelo, CambiaModelo {

    private final Map<String, RecSys> recomendadores;
    private static final String KNN = "KNN";
    private static final String KMEANS = "KEMANS";
    private static final String TRAIN = "train";

    List<String> songs;
    List<String> recommendations;
    private InformaVista vista;

    public ModeloCanciones() throws IOException, MasDatosQueGruposException {
        // Ruta genérica a los conjuntos de datos
        String sep = System.getProperty("file.separator");
        String ruta = "src" + sep + "files" + sep + "songs_files";

        // Nos guardamos una lista para los títulos de las canciones
        songs = LectorSongs.readNames(ruta+sep+"songs_test_names.csv");

        // En un mapa guardamos el nombre del algoritmo/conjunto y su ruta correspondientee
        Map<String,String> filenames = new HashMap<>();
        filenames.put(KNN + TRAIN,ruta+sep+"songs_train.csv");
        filenames.put(KNN +"test",ruta+sep+"songs_test.csv");
        filenames.put(KMEANS + TRAIN,ruta+sep+"songs_train_withoutnames.csv");
        filenames.put(KMEANS +"test",ruta+sep+"songs_test_withoutnames.csv");

        // Tablas de datos, tanto entrenamiento como test
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {TRAIN, "test"};
        for (String stage : stages) {
            tables.put(KNN + stage, new CSVLabeledFileReader(filenames.get("KNN" + stage)).readTableFromSource());
            tables.put(KMEANS + stage, new CSVUnlabeledFileReader(filenames.get(KMEANS + stage)).readTableFromSource());
        }

        // En otro mapa guardamos los recomendadores
        recomendadores = new HashMap<>();
        recomendadores.put(KNN + "#" + "euclidean", new RecSys(new KNN(new EuclideanDistance())));
        recomendadores.put(KNN + "#" + "manhattan",new RecSys(new KNN(new ManhattanDistance())));
        recomendadores.put(KMEANS + "#" + "euclidean",new RecSys(new Kmeans(15, 200, 4321, new EuclideanDistance())));
        recomendadores.put(KMEANS + "#" + "manhattan",new RecSys(new Kmeans(15, 200, 4321, new ManhattanDistance())));

        for(Map.Entry<String, RecSys> entry: recomendadores.entrySet()) {
            String etiqueta = entry.getKey();
            RecSys recomendador = entry.getValue();
            String[] algoritmoYDistancia = etiqueta.split("#");
            recomendador.train(tables.get(algoritmoYDistancia[0] + TRAIN));
            recomendadores.get(etiqueta).run(tables.get(algoritmoYDistancia[0]+"test"), songs);
        }

    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

    @Override
    public void avisaAVistaDeCanciones() {
        vista.listaCanciones();
    }

    @Override
    public List<String> getCanciones() {
        return songs;
    }

    @Override
    public void buscaRecomendacionesYAvisaVista(String algorithm, String distance, String song, int numRecommendations) {
        recommendations = recomendadores.get(algorithm + "#" + distance).recommend(song, numRecommendations);
        vista.listaRecomendaciones();
    }

    @Override
    public List<String> getRecomendaciones() {
        return recommendations;
    }
}
