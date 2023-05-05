package mvc.controlador;

import mvc.vista.InterrogaVista;
import mvc.vista.VistaResultado;

import java.util.List;

public interface Controlador {
    // Métodos que necesita la Vista.
    // En esta Aplicación e Implementación del patrón MVC consideramos que el Modelo no cambia, simplemente se crea.
    // Siendo ese el caso, nos queda la duda de si el controlador es necesario, pues la Vista podría encargarse sola
    // de preguntar al Modelo, pues no necesita cambios.
    // Por esa razón hemos decidido que el Controlador actuará de intermediario para obtener información
    List<String> getCanciones();
    List<String> recomiendaCanciones();
    void setInterrogaVista(InterrogaVista vista);
}
