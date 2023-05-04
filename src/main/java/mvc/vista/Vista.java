package mvc.vista;

import algoritmos.MasDatosQueGruposException;
import javafx.stage.Stage;
import mvc.controlador.Controlador;
import mvc.modelo.Algoritmos;
import mvc.modelo.Distancias;
import mvc.modelo.Modelo;

import java.util.List;

public interface Vista {
    //Métodos que necesita el Controlador
  /*  Algoritmos getAlgoritmo();
    Distancias getDistancia();
    String getSong();
    int getNumeroRecomendaciones();
    */


    void setControlador(Controlador controlador);
    void setModelo(Modelo modelo);

    List<String> muestraCanciones();
    void crearGUI() throws MasDatosQueGruposException;

    List<String> recomendaciones() throws MasDatosQueGruposException;


    //Métodos que necesita el Modelo
}
