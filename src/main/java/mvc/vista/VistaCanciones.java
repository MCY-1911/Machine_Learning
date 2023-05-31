package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;

public class VistaCanciones implements InformaVista {
    private final Stage stage;
    SelectionMode politicaSeleccion = SelectionMode.MULTIPLE;
    private Controlador controlador;
    private InterrogaModelo modelo;
    VBox display = new VBox();
    ToggleGroup grupoAlgoritmo = new ToggleGroup();
    ToggleGroup grupoDistance = new ToggleGroup();
    ListView<String> cancionesMostradas;


    public VistaCanciones(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public SelectionMode getPoliticaSeleccion() {
        return politicaSeleccion;
    }

    public void setPoliticaSeleccion(SelectionMode politicaSeleccion) {
        this.politicaSeleccion = politicaSeleccion;
    }

    public void crearGUICanciones() {

        stage.setTitle("Song Recommender");

        Button recomendar = new Button("Recommend");
        recomendar.setDisable(true);
        recomendar.setOnAction(actionEvent -> createRecommendationView());

        crearOpcionesAlgoritmo(recomendar);
        crearOpcionesDistancia(recomendar);
        crearListaCanciones(recomendar);

        display.getChildren().add(recomendar);
        display.setSpacing(5);
        display.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(display);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void crearOpcionesAlgoritmo(Button recomendar) {
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

        // Escuchador para comprobar si todas las opciones están listas y entonces habilitar el botón Recommend
        grupoAlgoritmo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
        });
    }

    private void crearOpcionesDistancia(Button recomendar) {
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

        // Escuchador para comprobar si todas las opciones están listas y entonces habilitar el botón Recommend
        grupoAlgoritmo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
        });
    }

    private void crearListaCanciones(Button recomendar) {
        //Lista de canciones
        Label labelLista = new Label("Song Titles");
        labelLista.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        ObservableList<String> canciones = FXCollections.observableArrayList(modelo.getCanciones());
        cancionesMostradas = new ListView<>(canciones);
        display.getChildren().addAll(labelLista, cancionesMostradas);

        //Añadimos un tooltip a la lista
        Tooltip consejo = new Tooltip("Double click for recommendations bases on this song");
        cancionesMostradas.setTooltip(consejo);

        // Escuchador para comprobar si todas las opciones están listas y entonces habilitar el botón Recommend
        cancionesMostradas.getSelectionModel().selectedItemProperty().addListener((item, valorAnterior, valorActual) -> {
            if(isAllSelected())
                recomendar.setDisable(false);
            // Además, actualizamos el texto de dicho botón
            recomendar.setText("Recommend on " + valorActual + "...");
        });

        // Escuchador para el doble-clic
        cancionesMostradas.setOnMouseClicked( e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 && isAllSelected()) {
                createRecommendationView();
            }
        });
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
        VistaResultado vistaRecomendaciones = new VistaResultado(this, algorithm, distance, song);
        vistaRecomendaciones.setControlador(controlador);
        controlador.setInterrogaVista(vistaRecomendaciones);
        try {
            vistaRecomendaciones.crearGUI();
            if (politicaSeleccion==SelectionMode.SINGLE)
                deshabilitarVentanaPrincipal();
        } catch (MasDatosQueGruposException e) {
            e.printStackTrace();
        }
    }

    private void deshabilitarVentanaPrincipal() {
        display.setDisable(true);
        stage.getScene().getRoot().setStyle("-fx-opacity: 0.5;");
    }

    public void habilitarVentanaPrincipal() {
        display.setDisable(false);
        stage.getScene().getRoot().setStyle("-fx-opacity: 1.0;");
    }
}
