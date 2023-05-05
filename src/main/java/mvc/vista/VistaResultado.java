package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;

public class VistaResultado implements InterrogaVista{

    final Stage ventana;
    String algorithm;
    String distance;
    String song;
    Controlador controlador;
    ListView<String> cancionesRecomendadas = new ListView<>();
    Spinner<Integer> spinner;


    public VistaResultado(final Stage propietario, String algorithm, String distance, String song) {
        this.ventana = new Stage();
        ventana.initOwner(propietario);
        this.algorithm = algorithm;
        this.distance = distance;
        this.song = song;
    }

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void crearGUI() throws MasDatosQueGruposException {
        ventana.setTitle("Recommend titles");

        HBox displaySpinner = new HBox();
        VBox displayGeneral = new VBox();

        //Número de recomendaciones
        Label labelnumRecomend = new Label("Number of recommendations:");

        // Creamos un Spinner con valor por defecto 5
        spinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 5);
        spinner.setValueFactory(valueFactory);


        displaySpinner.getChildren().addAll(labelnumRecomend, spinner);
        displaySpinner.setSpacing(5);
        displayGeneral.getChildren().add(displaySpinner);

        //Título de la canción
        Label titulo = new Label("If you liked\" " + song + "\" you might like");
        Label info = new Label("Algorithm: " + algorithm + " " + "Distance: " + distance + "\n");
        displayGeneral.getChildren().addAll(titulo, info);


        //Lista de canciones
        cancionesRecomendadas.getItems().addAll(controlador.recomiendaCanciones());
        displayGeneral.getChildren().add(cancionesRecomendadas);

        valueFactory.valueProperty().addListener( (obs, oldValue, newValue) -> {
            cancionesRecomendadas.getItems().clear();
            cancionesRecomendadas.getItems().addAll(controlador.recomiendaCanciones());
        });

        // Botón de Close
        Button buttonBack = new Button("Close");
        displayGeneral.getChildren().add(buttonBack);
        buttonBack.setOnAction(actionEvent -> ventana.close());

        Scene scene = new Scene(displayGeneral);
        ventana.setScene(scene);
        ventana.show();
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
