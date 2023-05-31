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
        ModeloCanciones impModelo = new ModeloCanciones();
        VistaCanciones impVista = new VistaCanciones(stage);
        //Podemos elegir entre poder mostrar varias ventanas de canciones recomendadas o solo una a la vez
        impVista.setPoliticaSeleccion(SelectionMode.SINGLE);
        ControladorCanciones impControlador = new ControladorCanciones();
        impControlador.setModelo(impModelo);
        impVista.setControlador(impControlador);
        impVista.setModelo(impModelo);
        impVista.crearGUICanciones();
    }
}
