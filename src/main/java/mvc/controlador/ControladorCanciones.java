package mvc.controlador;

import mvc.modelo.InterrogaModelo;
import mvc.vista.InterrogaVista;

import java.util.List;

public class ControladorCanciones implements Controlador{

    InterrogaVista preguntaVista;
    // Vamos a hacer un pequeño cambio por claridad, en esta aplicación del modelo se genera, pero nunca
    // se modifica. Es decir, el Controlador no necesita interactuar con el Modelo. La Vista podría preguntar
    // directamente al Modelo. Para más claridad cambiaremos ese comportamiento, la Vista informará al Controlador
    // de que necesita recomendaciones, y será el controlador el encargado de preguntar al modelo y devolver a la Vista
    InterrogaModelo modelo;

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void setInterrogaVista(InterrogaVista vista) {
        this.preguntaVista = vista;
    }

    public ControladorCanciones(){
        super();
    }

    @Override
    public List<String> recomiendaCanciones() {
        String algorithm = preguntaVista.getAlgorithm();
        String distance = preguntaVista.getDistance();
        String song = preguntaVista.getSong();
        int numRecommendations = preguntaVista.getNumRecommendations();
        return modelo.getRecomendaciones(algorithm, distance, song, numRecommendations);
    }


}
