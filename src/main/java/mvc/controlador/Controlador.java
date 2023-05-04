package mvc.controlador;

import algoritmos.MasDatosQueGruposException;
import mvc.modelo.Modelo;

import java.util.List;

public interface Controlador {

    void setModelo(final Modelo modelo);
    List<String> vuelveAListaCanciones();

    List<String> recomiendaCanciones(String song, String algoritmo, String distancia, int num) throws MasDatosQueGruposException;

}
