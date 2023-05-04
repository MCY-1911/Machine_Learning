package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.Modelo;

import java.util.List;

public class VistaResultado implements Vista{

    final Stage stage;
    String algoritmo;
    String distancia;
    String song;
    int numRecomend;

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }

    private Controlador controlador;
    private Modelo modelo;

    public VistaResultado(final Stage stage, String algoritmo, String distancia, String song) {
        this.stage = stage;
        this.algoritmo = algoritmo;
        this.distancia = distancia;
        this.song = song;
        this.numRecomend = 5;
    }

    @Override
    public void crearGUI() throws MasDatosQueGruposException {

        stage.setTitle("Song Recommender");

        HBox display = new HBox();
        VBox display2 = new VBox();


        //Número de recomendaciones
        Label labelnumRecomend = new Label("Numer of remcommendations");

        Spinner<Integer> spinner = new Spinner<>();

// Se define el rango de valores permitidos
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);

// Se agrega un listener para detectar cambios en el valor del Spinner
        valueFactory.valueProperty().addListener((obs, oldValue, newValue) -> {
            numRecomend = newValue;

        });

        spinner.setValueFactory(valueFactory);



        display.getChildren().addAll(labelnumRecomend, spinner);
        display.setSpacing(5);

        //Titulo de la canción
        Label titulo = new Label("If you liked\" " + song + "\" you might like");

        display2.getChildren().add(titulo);


        VBox root = new VBox(display, display2);
        //Lista de canciones
        ObservableList<String> canciones = FXCollections.observableArrayList(recomendaciones());
        root.getChildren().add((Node) canciones);


        //Boton de close
        Button back = new Button("Close");
        back.setOnAction(actionEvent -> {
            stage.close();
            VistaCanciones vistaCanciones = new VistaCanciones(stage);
            vistaCanciones.crearGUI();
        });


        root.getChildren().add(back);

        Scene scene = new Scene(display, 300, 500);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public List<String> recomendaciones() throws MasDatosQueGruposException {
        return controlador.recomiendaCanciones(song, algoritmo, distancia, numRecomend);
    }


    @Override
    public List<String> muestraCanciones() {
        return controlador.vuelveAListaCanciones();
    }


}
