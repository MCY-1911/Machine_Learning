package mvc.modelo;

public interface CambiaModelo {
    //Métodos que necesita el Controlador
    void avisaAVistaDeCanciones();
    void buscaRecomendacionesYAvisaVista(String algorithm, String distance, String song, int numRecommendations);
}
