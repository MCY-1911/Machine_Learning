package mvc.controlador;

import mvc.modelo.InterrogaModelo;
import mvc.vista.InterrogaVista;


public class ControladorCanciones implements Controlador{

    InterrogaVista vista;
    InterrogaModelo modelo;

    public ControladorCanciones(){
        super();
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

}
