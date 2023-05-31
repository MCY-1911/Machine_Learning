package mvc.Arrancador;

import javafx.application.Application;
import javafx.scene.control.SelectionMode;
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
        VistaCanciones impVista = new VistaCanciones(stage);
        ControladorCanciones impControlador = new ControladorCanciones();
        ModeloCanciones impModelo = new ModeloCanciones();
        //Podemos elegir entre poder mostrar varias ventanas de canciones recomendadas o solo una a la vez
        impControlador.setModelo(impModelo);
        impVista.setControlador(impControlador);
        impVista.setModelo(impModelo);

        impVista.setPoliticaSeleccion(SelectionMode.MULTIPLE);
        impVista.crearGUICanciones();
    }
}
