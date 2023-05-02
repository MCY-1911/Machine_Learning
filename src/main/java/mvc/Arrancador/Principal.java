package mvc.Arrancador;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc.modelo.SongRecomendationApplication;
import mvc.vista.Seleccionador;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SongRecomendationApplication impModelo = new SongRecomendationApplication();
        Seleccionador impVista = new Seleccionador(stage);
        impVista.setModelo(impModelo);
        stage.setTitle("Song Recommender");
        impVista.crearGUI();
    }
}
