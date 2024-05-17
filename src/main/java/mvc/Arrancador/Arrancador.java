package mvc.arrancador;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc.controlador.ControladorCanciones;
import mvc.modelo.ModeloCanciones;
import mvc.vista.VistaCanciones;

public class Arrancador extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Creación roles
        VistaCanciones impVista = new VistaCanciones(stage);
        ControladorCanciones impControlador = new ControladorCanciones();
        ModeloCanciones impModelo = new ModeloCanciones();

        // Inyección dependencias
        impControlador.setModelo(impModelo);
        impControlador.setVista(impVista);
        impVista.setControlador(impControlador);
        impVista.setModelo(impModelo);
        impModelo.setVista(impVista);

        // Muestra de GUI
        impVista.crearGUICanciones();
    }
}
