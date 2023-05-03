package mvc.modelo;

import java.util.List;

public interface Modelo {
    //Métodos que necesita el Controlador
    List<String> getRecomendaciones(String song, String algoritmo, String distancia, int numRecomendaciones);

    //Métodos que necesita la Vista
    List<String> getCanciones();
}
