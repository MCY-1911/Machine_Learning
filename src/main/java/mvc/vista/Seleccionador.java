package mvc.vista;

import com.sun.scenario.effect.Blend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.Modelo;

import java.util.List;


public class Seleccionador implements Vista {

    private final Stage stage;

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }

    private Controlador controlador;
    private Modelo modelo;

    public Seleccionador(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void crearGUI() {
        //stage es el escenario, la ventana
        //scene es la escena, lo que se representa dentro de la ventana
        //FALTAN ESCUCHADORES

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

        //


        Scene scene = new Scene(display, 300, 500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public int getAlgoritmo() {
        return 0;
    }

    @Override
    public int getDistancia() {
        return 0;
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
