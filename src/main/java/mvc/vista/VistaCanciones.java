package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import java.awt.Panel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.Modelo;

public class VistaCanciones implements Vista {
    private final Stage stage;
    private Controlador controlador;
    private Modelo modelo;
    String algoritmo;
    String selectedSong;
    String distancia;
    int numRecomend;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public VistaCanciones(Stage stage) {
        this.stage = stage;
    }

    public void crearGUI() {
        VBox display = new VBox();

        //Primer titulo con los botones
        Label labelAlgoritmo = new Label("Recommendation Type");
        ToggleGroup grupoAlgoritmo = new ToggleGroup();
        RadioButton knn = new RadioButton(" Recommend based on song features");
        knn.setToggleGroup(grupoAlgoritmo);
        knn.setUserData("knn");
        RadioButton kmeans = new RadioButton(" Recommend based on guessed genre");
        kmeans.setToggleGroup(grupoAlgoritmo);
        kmeans.setUserData("kmeans");
        display.getChildren().addAll(new Node[]{labelAlgoritmo, knn, kmeans});

        //Segundo titulo con los botones
        Label labelDistance = new Label("Distance Type");
        ToggleGroup grupoDistance = new ToggleGroup();
        RadioButton euclidean = new RadioButton(" Euclidean ");
        euclidean.setToggleGroup(grupoDistance);
        euclidean.setUserData("euclidean");
        RadioButton manhattan = new RadioButton(" Manhattan");
        manhattan.setToggleGroup(grupoDistance);
        manhattan.setUserData("manhattan");
        display.getChildren().addAll(new Node[]{labelDistance, euclidean, manhattan});

        //Lista de canciones
        Label labelLista = new Label("Song Titles");
        ObservableList<String> canciones = FXCollections.observableArrayList(this.muestraCanciones());
        ListView cancionesMostradas = new ListView(canciones);
        display.getChildren().addAll(new Node[]{labelLista, cancionesMostradas});



        //Accion del boton Recommend
        Button recomendar = new Button("Recommend");
        recomendar.setOnAction((actionEvent) -> {
            this.selectedSong = (String)cancionesMostradas.getSelectionModel().getSelectedItem();
            Toggle toggleAlgoritmo = grupoAlgoritmo.getSelectedToggle();
            Toggle toggleDistancia = grupoDistance.getSelectedToggle();



           //Si estan todos los parametros pasa a la segunda vista
            if (grupoAlgoritmo.getSelectedToggle() != null && grupoDistance.getSelectedToggle() != null && this.selectedSong != null) {
                this.stage.close();
                this.algoritmo = toggleAlgoritmo.getUserData().toString();
                this.distancia = toggleDistancia.getUserData().toString();
                VistaResultado vista2 = new VistaResultado(this.stage, this.algoritmo, this.distancia, this.selectedSong, 5);
                vista2.setControlador(this.controlador);
                vista2.setModelo(this.modelo);
                try {
                    vista2.crearGUI();
                } catch (MasDatosQueGruposException var7) {
                    throw new RuntimeException(var7);
                }

                // Si no estan todos los parametros marcados salta un error
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing selection");
                alert.setContentText("Please select an option for each category.");
                alert.showAndWait();
            }
        });

        display.getChildren().add(recomendar);
        Scene scene = new Scene(display, 300.0, 500.0);
        this.stage.setScene(scene);
        this.stage.show();
    }

    @Override
    public List<String> recomendaciones() throws MasDatosQueGruposException {
       return controlador.recomiendaCanciones(selectedSong, algoritmo, distancia, numRecomend);
    }


    public List<String> muestraCanciones() {
        return this.controlador.vuelveAListaCanciones();
    }
}
