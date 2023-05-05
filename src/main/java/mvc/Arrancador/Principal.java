package mvc.Arrancador;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc.controlador.ControladorCanciones;
import mvc.modelo.ModeloCanciones;
import mvc.vista.VistaCanciones;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ModeloCanciones impModelo = new ModeloCanciones();
        VistaCanciones impVista = new VistaCanciones(stage);
        ControladorCanciones impControlador = new ControladorCanciones();

        impControlador.setModelo(impModelo);
        impVista.setControlador(impControlador);

        impVista.crearGUICanciones();
    }
}
