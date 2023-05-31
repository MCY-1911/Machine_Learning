package mvc.modelo;

import mvc.vista.InterrogaVista;

import java.util.List;

public interface InterrogaModelo {
    void setVista(InterrogaVista vista);
    //Métodos que emplea el Controlador
    List<String> getCanciones();
    List<String> getRecomendaciones();

}
