package mvc.vista;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class seleccionador extends Application implements Vista {


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //stage es el escenario, la ventana
        //scene es la escena, lo que se representa dentro de la ventana
        stage.setTitle("Song Recommender");

        //Primera opción
        Label labelAlgoritmo = new Label("Recommendation Type");
        ToggleGroup grupoAlgoritmo = new ToggleGroup();

        RadioButton knn = new RadioButton(" Recommend based on song features");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");

        knn.setToggleGroup(grupoAlgoritmo);
        kmeans.setToggleGroup(grupoAlgoritmo);

        //Segunda opción
        Label labelDistance = new Label("Distance Type");
        ToggleGroup grupoDistance = new ToggleGroup();

        RadioButton euclidean = new RadioButton(" Euclidean ");
        RadioButton manhattan = new RadioButton(" Manhattan");

        euclidean.setToggleGroup(grupoDistance);
        manhattan.setToggleGroup(grupoDistance);

        //Tercera opción
        Label labelLista = new Label("Song Titles");




        VBox vBox = new VBox(labelAlgoritmo, knn, kmeans, labelDistance, euclidean, manhattan, labelLista);

        Scene scene = new Scene(vBox, 400,300);
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
