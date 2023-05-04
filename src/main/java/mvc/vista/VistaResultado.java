package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;

public class VistaResultado implements InterrogaVista{

    final Stage stage;
    String algorithm;
    String distance;
    String song;

    Spinner<Integer> spinner = new Spinner<>();
    int numRecommendations = 0;

    // Solo necesitamos una referencia al controlador para conseguir los datos
    Controlador controlador;
    // Y otro para volver a la vista anterior
    VistaCanciones vistaCanciones;

    public VistaResultado(final Stage stage, String algorithm, String distance, String song, VistaCanciones vistaCanciones) {
        this.stage = stage;
        this.algorithm = algorithm;
        this.distance = distance;
        this.song = song;
        this.vistaCanciones = vistaCanciones;
    }

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void crearGUI() throws MasDatosQueGruposException {
        stage.setTitle("Recommend titles");

        HBox displaySpinner = new HBox();
        VBox displayGeneral = new VBox();

        //Número de recomendaciones
        Label labelnumRecomend = new Label("Number of remcommendations:");

        // Creamos un Spinner con valor por defecto 5
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 5);
        spinner.setValueFactory(valueFactory);


        displaySpinner.getChildren().addAll(labelnumRecomend, spinner);
        displaySpinner.setSpacing(5);
        displayGeneral.getChildren().add(displaySpinner);

        //Título de la canción
        Label titulo = new Label("If you liked\" " + song + "\" you might like");
        displayGeneral.getChildren().add(titulo);


        //Lista de canciones
        ObservableList<String> canciones = FXCollections.observableArrayList(controlador.getCanciones());
        ListView cancionesRecomendadas = new ListView(canciones);
        displayGeneral.getChildren().add(cancionesRecomendadas);

        valueFactory.valueProperty().addListener( (obs, oldValue, newValue) -> {
            numRecommendations = newValue;
        });

        //Boton de Close
        Button buttonBack = new Button("Close");
        displayGeneral.getChildren().add(buttonBack);
        buttonBack.setOnAction(actionEvent -> {
            stage.close();
            vistaCanciones.crearGUICanciones();
        });



        Scene scene = new Scene(displayGeneral);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String getDistance() {
        return distance;
    }

    @Override
    public String getSong() {
        return song;
    }

    @Override
    public int getNumRecommendations() {
        return spinner.getValue();
    }
}
