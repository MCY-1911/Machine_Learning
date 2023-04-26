package interfazGrafica.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class seleccionador extends Application {


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

        knn.setToggleGroup(grupoDistance);
        kmeans.setToggleGroup(grupoDistance);

        //Tercera opción
        Label labelLista = new Label("Song Titles");

        VBox vBox = new VBox(labelAlgoritmo, knn, kmeans, labelDistance, euclidean, manhattan, labelLista);

        Scene scene = new Scene(vBox, 400,300);
        stage.setScene(scene);
        stage.show();
    }
}
