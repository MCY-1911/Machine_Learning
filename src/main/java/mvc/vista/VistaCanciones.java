package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;

public class VistaCanciones implements InformaVista {
    private final Stage stage;
    private Controlador controlador;
    ToggleGroup grupoAlgoritmo = new ToggleGroup();
    ToggleGroup grupoDistance = new ToggleGroup();
    ListView<String> cancionesMostradas;



    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public VistaCanciones(Stage stage) {
        this.stage = stage;
    }

    public void crearGUICanciones() {
        stage.setTitle("Song Recommender");
        // Nodo de posicionamiento que contendrá todas las opciones
        VBox display = new VBox();

        //Primer título con los botones
        Label labelAlgoritmo = new Label("Recommendation Type");
        RadioButton knn = new RadioButton(" Recommend based on song features");
        knn.setToggleGroup(grupoAlgoritmo);
        knn.setUserData("knn");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");
        kmeans.setToggleGroup(grupoAlgoritmo);
        kmeans.setUserData("kmeans");
        display.getChildren().addAll(labelAlgoritmo, knn, kmeans);


        //Segundo título con los botones
        Label labelDistance = new Label("Distance Type");
        RadioButton euclidean = new RadioButton(" Euclidean ");
        euclidean.setToggleGroup(grupoDistance);
        euclidean.setUserData("euclidean");
        RadioButton manhattan = new RadioButton(" Manhattan");
        manhattan.setToggleGroup(grupoDistance);
        manhattan.setUserData("manhattan");
        display.getChildren().addAll(labelDistance, euclidean, manhattan);

        //Lista de canciones
        Label labelLista = new Label("Song Titles");
        ObservableList<String> canciones = FXCollections.observableArrayList(controlador.getCanciones());
        cancionesMostradas = new ListView<>(canciones);
        display.getChildren().addAll(labelLista, cancionesMostradas);


        //Acción del botón Recommend
        Button recomendar = new Button("Recommend");
        recomendar.setOnAction(actionEvent -> {
            //Si no están todos los parámetros, avisa al usuario con un mensaje de error
            if (grupoAlgoritmo.getSelectedToggle() == null
                    || grupoDistance.getSelectedToggle() == null
                    || cancionesMostradas.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing selection");
                alert.setContentText("Please select an option for each category.");
                alert.showAndWait();
            } else {
                // Si los tenemos, los guardamos como atributos de una nueva Vista
                String algorithm = grupoAlgoritmo.getSelectedToggle().getUserData().toString();
                String distance = grupoDistance.getSelectedToggle().getUserData().toString();
                String song = cancionesMostradas.getSelectionModel().getSelectedItem();
                VistaResultado vistaRecomendaciones = new VistaResultado(this.stage, algorithm, distance, song);
                vistaRecomendaciones.setControlador(controlador);
                controlador.setInterrogaVista(vistaRecomendaciones);
                try {
                    vistaRecomendaciones.crearGUI();
                } catch (MasDatosQueGruposException e) {
                    e.printStackTrace();
                }
            }
        });

        display.getChildren().add(recomendar);

        Scene scene = new Scene(display);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
