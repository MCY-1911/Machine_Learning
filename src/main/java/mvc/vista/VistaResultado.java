package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mvc.modelo.InterrogaModelo;

public class VistaResultado implements InterrogaVista{

    final Stage escenario;
    final VistaCanciones vistaPrincipal;
    String algorithm;
    String distance;
    String song;
    InterrogaModelo modelo;
    ListView<String> cancionesRecomendadas = new ListView<>();
    Spinner<Integer> spinner;


    public VistaResultado(VistaCanciones vistaPrincipal, String algorithm, String distance, String song) {
        this.escenario = new Stage();
        this.vistaPrincipal = vistaPrincipal;
        escenario.initOwner(vistaPrincipal.getStage());
        this.algorithm = algorithm;
        this.distance = distance;
        this.song = song;

        escenario.setOnCloseRequest(event -> volverAVentanaPrincipal());
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void crearGUI() throws MasDatosQueGruposException {
        escenario.setTitle("Recommend titles: " + song);
        VBox displayGeneral = new VBox();

        crearTitulo(displayGeneral);
        crearSpinner(displayGeneral);

        //Lista de canciones
        cancionesRecomendadas.getItems().addAll(modelo.getRecomendaciones());
        displayGeneral.getChildren().add(cancionesRecomendadas);

        crearBotonClose(displayGeneral);
        displayGeneral.setSpacing(5);
        displayGeneral.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(displayGeneral);
        escenario.setScene(scene);
        escenario.show();
    }

    private void crearTitulo(VBox displayGeneral) {
        //Título de la canción
        Label preTitulo = new Label("If you liked:");
        Label titulo = new Label(song);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label postTitulo = new Label("You might like");
        displayGeneral.getChildren().addAll(preTitulo,titulo,postTitulo);
    }

    private void crearSpinner(VBox displayGeneral) {

        HBox displaySpinner = new HBox();

        //Texto para número de recomendaciones
        Label etiquetaNumeroRecomendaciones = new Label("Number of recommendations:");

        // Creamos un Spinner con valor por defecto 5
        spinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 5);
        spinner.setValueFactory(valueFactory);

        // Añadir un filtro para aceptar solo números
        spinner.setEditable(true);
        spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                spinner.getEditor().setText(newValue.replaceAll("\\D", ""));
            }
        });

        // Escuchador para obtener las recomendaciones dinámicamente
        valueFactory.valueProperty().addListener( (obs, oldValue, newValue) -> {
            cancionesRecomendadas.getItems().clear();
            cancionesRecomendadas.getItems().addAll(modelo.getRecomendaciones());
        });

        // Lo agregamos a la escena
        displaySpinner.getChildren().addAll(etiquetaNumeroRecomendaciones, spinner);
        displaySpinner.setSpacing(5);
        displayGeneral.getChildren().add(displaySpinner);
    }

    private void crearBotonClose(VBox displayGeneral) {
        Button buttonBack = new Button("Close");
        buttonBack.setOnAction(actionEvent -> volverAVentanaPrincipal());
        displayGeneral.getChildren().add(buttonBack);
    }

    private void volverAVentanaPrincipal() {
        escenario.close();
        if (vistaPrincipal.getPoliticaSeleccion() == SelectionMode.SINGLE)
            vistaPrincipal.habilitarVentanaPrincipal();
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
