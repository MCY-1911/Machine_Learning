package mvc.modelo;

import java.util.List;

public interface InterrogaModelo {
    //Métodos que emplea el Controlador
    List<String> getCanciones();
    List<String> getRecomendaciones(String algorithm, String distance, String song, int numRecommendations);

}
