package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        Button recomendar = new Button("Recommend");
        recomendar.setDisable(true);

        //Primer título con los botones
        Label labelAlgoritmo = new Label("Recommendation Type");
        labelAlgoritmo.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        RadioButton knn = new RadioButton(" Recommend based on song features");
        knn.setToggleGroup(grupoAlgoritmo);
        knn.setUserData("knn");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");
        kmeans.setToggleGroup(grupoAlgoritmo);
        kmeans.setUserData("kmeans");
        display.getChildren().addAll(labelAlgoritmo, knn, kmeans);

        grupoAlgoritmo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
        });


        //Segundo título con los botones
        Label labelDistance = new Label("Distance Type");
        labelDistance.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        RadioButton euclidean = new RadioButton(" Euclidean ");
        euclidean.setToggleGroup(grupoDistance);
        euclidean.setUserData("euclidean");
        RadioButton manhattan = new RadioButton(" Manhattan");
        manhattan.setToggleGroup(grupoDistance);
        manhattan.setUserData("manhattan");
        display.getChildren().addAll(labelDistance, euclidean, manhattan);

        grupoAlgoritmo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
        });

        //Lista de canciones
        Label labelLista = new Label("Song Titles");
        labelLista.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        ObservableList<String> canciones = FXCollections.observableArrayList(controlador.getCanciones());
        cancionesMostradas = new ListView<>(canciones);
        display.getChildren().addAll(labelLista, cancionesMostradas);

        cancionesMostradas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
        });

        recomendar.setOnAction(actionEvent -> {
            createRecommendationView();
        });

        display.getChildren().add(recomendar);
        display.setSpacing(5);
        display.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(display);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private boolean isAllSelected() {
       return (grupoAlgoritmo.getSelectedToggle() != null
                && grupoDistance.getSelectedToggle() != null
                && cancionesMostradas.getSelectionModel().getSelectedItem() != null);

    }

    private void createRecommendationView() {
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

    private void alertUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing selection");
        alert.setContentText("Please select an option for each category.");
        alert.showAndWait();
    }

}
