package mvc.vista;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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


public class VistaCanciones implements InformaVista, InterrogaVista {
    private final Stage stage;
    private static final String ARIAL = "Arial";
    private Controlador controlador;
    private InterrogaModelo modelo;
    private VistaResultado vistaResultado;

    // Componentes de la Vista
    VBox display = new VBox();
    ToggleGroup grupoAlgoritmo = new ToggleGroup();
    ToggleGroup grupoDistance = new ToggleGroup();
    ListView<String> cancionesMostradas;
    FilteredList<String> filteredList;
    Button recomendar;


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

    public void crearGUICanciones() {

        stage.setTitle("Song Recommender");

        crearBotonRecomendar();
        crearOpcionesAlgoritmo();
        crearOpcionesDistancia();
        crearListaCanciones();

        display.getChildren().add(recomendar);
        display.setSpacing(5);
        display.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(display);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void crearBotonRecomendar() {
        recomendar = new Button("Recommend");
        recomendar.setDisable(true);
        recomendar.setOnAction(actionEvent -> createRecommendationView());
    }

    private void crearOpcionesAlgoritmo() {
        //Primer título con los botones
        Label labelAlgoritmo = new Label("Recommendation Type");
        labelAlgoritmo.setFont(Font.font(ARIAL,FontWeight.BOLD, 14));
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

    private void crearOpcionesDistancia() {
        //Segundo título con los botones
        Label labelDistance = new Label("Distance Type");
        labelDistance.setFont(Font.font(ARIAL,FontWeight.BOLD, 14));
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

    private void crearListaCanciones() {

        // Etiqueta del apartado
        Label labelLista = new Label("Song Titles");
        labelLista.setFont(Font.font(ARIAL,FontWeight.BOLD, 14));
        display.getChildren().addAll(labelLista);

        // Listado de canciones
        controlador.pideCanciones();

        // Barra de búsqueda
        Label labelBuscar = new Label("Search: ");
        TextField searchBar = new TextField();
        searchBar.setPromptText("Enter a song title");
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(cancion -> newValue.isEmpty() || cancion.toLowerCase().contains(newValue.toLowerCase()));
            recomendar.setText("Recommend");
            recomendar.setDisable(true);
        });

        display.getChildren().addAll(labelBuscar, searchBar, cancionesMostradas);

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

    private void createRecommendationView() {
        // Ligamos la subclase, para otro programador, el uso de estas dos clases de ve como usar solo esta
        vistaResultado = new VistaResultado(this);
        vistaResultado.crearGUI();
        deshabilitarVentanaPrincipal();
    }

    // Métodos que necesita el Modelo
    @Override
    public void listaCanciones() {
        ObservableList<String> canciones = FXCollections.observableArrayList(modelo.getCanciones());
        filteredList = new FilteredList<>(canciones);
        cancionesMostradas = new ListView<>(filteredList);
    }

    @Override
    public void listaRecomendaciones() {
        vistaResultado.listaRecomendaciones(modelo.getRecomendaciones());
    }


    // Métodos que necesita el Controlador
    @Override
    public String getAlgorithm() {
        return grupoAlgoritmo.getSelectedToggle().getUserData().toString();
    }

    @Override
    public String getDistance() {
        return grupoDistance.getSelectedToggle().getUserData().toString();
    }

    @Override
    public String getSong() {
        return cancionesMostradas.getSelectionModel().getSelectedItem();
    }

    @Override
    public int getNumRecommendations() {
        return vistaResultado.getNumRecommendations();
    }

    // Métodos necesarios por VistaResultado
    private void deshabilitarVentanaPrincipal() {
        display.setDisable(true);
        stage.getScene().getRoot().setStyle("-fx-opacity: 0.5;");
    }

    public void habilitarVentanaPrincipal() {
        display.setDisable(false);
        stage.getScene().getRoot().setStyle("-fx-opacity: 1.0;");
    }

    private boolean isAllSelected() {
        return (grupoAlgoritmo.getSelectedToggle() != null
                && grupoDistance.getSelectedToggle() != null
                && cancionesMostradas.getSelectionModel().getSelectedItem() != null);
    }

    void llamaControladorParaRecomendaciones(){
        controlador.pideRecomendaciones();
    }
}
