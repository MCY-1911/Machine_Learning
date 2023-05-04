package mvc.controlador;

import java.util.List;

public interface Controlador {
    //Métodos que necesita la Vista
    List<String> getCanciones();
    List<String> recomiendaCanciones();

}
