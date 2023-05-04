package mvc.modelo;

import algoritmos.MasDatosQueGruposException;

import java.util.List;

public interface Modelo {
    //Métodos que necesita el Controlador
    List<String> getRecomendaciones(String song, String algoritmo, String distancia, int num) throws MasDatosQueGruposException;
    //Métodos que necesita la Vista
    List<String> getCanciones();
}
