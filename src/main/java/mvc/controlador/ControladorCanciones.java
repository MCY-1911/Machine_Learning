package mvc.controlador;

import algoritmos.MasDatosQueGruposException;
import mvc.modelo.Modelo;

import java.util.List;

public class ControladorCanciones implements Controlador{
    Modelo modelo;

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }
    public ControladorCanciones(){

    }

    @Override
    public List<String> recomiendaCanciones(String song, String algoritmo, String distancia, int num) throws MasDatosQueGruposException {

        return modelo.getRecomendaciones(song, algoritmo, distancia, num);
    }


    @Override
    public List<String> vuelveAListaCanciones() {
        return modelo.getCanciones();
    }
}
