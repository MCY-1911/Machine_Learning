package mvc.vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.Algoritmos;
import mvc.modelo.Distancias;
import mvc.modelo.Modelo;

import java.util.List;


public class VistaCanciones implements Vista {

    private final Stage stage;

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }

    private Controlador controlador;
    private Modelo modelo;

    public VistaCanciones(final Stage stage) {
        this.stage = stage;
    }
    
    public void crearGUI() {
        //stage es el escenario, la ventana
        //scene es la escena, lo que se representa dentro de la ventana

        VBox display = new VBox();

        //Primera opción
        Label labelAlgoritmo = new Label("Recommendation Type");
        ToggleGroup grupoAlgoritmo = new ToggleGroup();

        RadioButton knn = new RadioButton(" Recommend based on song features");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");

        knn.setToggleGroup(grupoAlgoritmo);
        kmeans.setToggleGroup(grupoAlgoritmo);
        display.getChildren().addAll(labelAlgoritmo ,knn, kmeans);

        //Segunda opción
        Label labelDistance = new Label("Distance Type");
        ToggleGroup grupoDistance = new ToggleGroup();

        RadioButton euclidean = new RadioButton(" Euclidean ");
        RadioButton manhattan = new RadioButton(" Manhattan");

        euclidean.setToggleGroup(grupoDistance);
        manhattan.setToggleGroup(grupoDistance);
        display.getChildren().addAll(labelDistance, euclidean, manhattan);

        //Tercera opción
        Label labelLista = new Label("Song Titles");
        ObservableList<String> canciones = FXCollections.observableArrayList(modelo.getCanciones());
        ListView cancionesMostradas = new ListView<>(canciones);
        display.getChildren().addAll(labelLista, cancionesMostradas);

        //Botón de confirmación
        Button recomendar = new Button("Recommend");
        display.getChildren().add(recomendar);

        Scene scene = new Scene(display);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public Algoritmos getAlgoritmo() {
        return Algoritmos.KNN;
    }

    @Override
    public Distancias getDistancia() {
        return Distancias.EUCLIDEAN;
    }

    @Override
    public String getSong() {
        return null;
    }

    @Override
    public int getNumeroRecomendaciones() {
        return 0;
    }

    @Override
    public void muestraRecomendaciones(List<String> recomendaciones) {

    }
}
