package interfazGrafica.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;


public class seleccionador extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Song Recommender");
        StackPane root = new StackPane();

        stage.setScene(new Scene(root, 500, 500));

        //Primera opción
        JLabel label1 = new JLabel("Recommendation Type");
        ToggleGroup grupo1 = new ToggleGroup();

        RadioButton knn = new RadioButton(" Recommend based on song features");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");

        knn.setToggleGroup(grupo1);
        kmeans.setToggleGroup(grupo1);

        //Segunda opción
        JLabel label2 = new JLabel("Distance Type");
        ToggleGroup grupo2 = new ToggleGroup();

        RadioButton euclidean = new RadioButton(" Euclidean ");
        RadioButton manhattan = new RadioButton(" Manhattan");

        knn.setToggleGroup(grupo2);
        kmeans.setToggleGroup(grupo2);

        //Tercera opción
        JLabel label3 = new JLabel("Song Titles");

        stage.show();
    }
}
