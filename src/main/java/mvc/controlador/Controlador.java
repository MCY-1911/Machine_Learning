package mvc.controlador;

import mvc.vista.InterrogaVista;

import java.util.List;

public interface Controlador {
    // Métodos que necesita la Vista.
    List<String> recomiendaCanciones();
    void setInterrogaVista(InterrogaVista vista);
}
