package mvc.controlador;

import mvc.modelo.CambiaModelo;
import mvc.vista.InterrogaVista;


public class ControladorCanciones implements Controlador {

    InterrogaVista vista;
    CambiaModelo modelo;

    public ControladorCanciones(){
        super();
    }

    public void setModelo(CambiaModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    @Override
    public void pideCanciones() {
        modelo.avisaAVistaDeCanciones();
    }

    @Override
    public void pideRecomendaciones() {
        String algoritmo = vista.getAlgorithm();
        String distancia = vista.getDistance();
        String cancion = vista.getSong();
        int numeroRecomendaciones = vista.getNumRecommendations();
        modelo.buscaRecomendacionesYAvisaVista(algoritmo, distancia, cancion, numeroRecomendaciones);
    }
}
