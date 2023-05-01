package mvc.modelo;

import java.util.List;

public interface Modelo {
    //Métodos que necesita el Controlador
    List<String> getRecomendaciones(String song);
    //Métodos que necesita la Vista
    List<String> getCanciones();
}
