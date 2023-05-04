package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    public VistaResultado(final Stage stage, String algoritmo, String distancia, String song, int numRecomend) {
        this.stage = stage;
        this.algoritmo = algoritmo;
        this.distancia = distancia;
        this.song = song;
        this.numRecomend = numRecomend;
    }

    @Override
    public void crearGUI() throws MasDatosQueGruposException {
            controlador.setModelo(modelo);
            stage.setTitle("Song Recommender");


            HBox display = new HBox();
            VBox display2 = new VBox();


            //Número de recomendaciones
            Label labelnumRecomend = new Label("Numer of remcommendations");

            Spinner<Integer> spinner = new Spinner<>();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 5);
            spinner.setValueFactory(valueFactory);

                valueFactory.valueProperty().addListener((obs, oldValue, newValue) -> {
                                System.out.println("Nuevo valor del spinner: " + newValue);
                VistaResultado newVista = new VistaResultado(stage, algoritmo, distancia, song, newValue);
                newVista.setModelo(modelo);
                newVista.setControlador(controlador);


                try {
                    System.out.println("hola");
                    newVista.crearGUI();
                } catch (MasDatosQueGruposException var7) {
                    throw new RuntimeException(var7);
                }
            });
            display.getChildren().addAll(labelnumRecomend, spinner);
            display.setSpacing(5);

            //Titulo de la canción
            Label titulo = new Label("If you liked\" " + song + "\" you might like");
            display2.getChildren().add(titulo);


            //Lista de canciones
            ObservableList<String> canciones = FXCollections.observableArrayList(recomendaciones());
            ListView cancionesMostradas = new ListView(canciones);


            //Boton de close
            Button buttonBack = new Button("Close");
            buttonBack.setOnAction(actionEvent -> {
                stage.close();
                VistaCanciones vistaCanciones = new VistaCanciones(stage);
                vistaCanciones.setControlador(controlador);
                vistaCanciones.setModelo(modelo);
                vistaCanciones.crearGUI();
            });

            VBox root = new VBox(display, display2, cancionesMostradas, buttonBack);
            root.setSpacing(5);

            Scene scene = new Scene(root, 300, 500);
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
