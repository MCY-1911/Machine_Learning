package mvc.vista;

import mvc.modelo.Algoritmos;
import mvc.modelo.Distancias;

import java.util.List;

public interface Vista {
    //Métodos que necesita el Controlador
    Algoritmos getAlgoritmo();
    Distancias getDistancia();
    String getSong();
    int getNumeroRecomendaciones();
    void muestraRecomendaciones(List<String> recomendaciones);

    //Métodos que necesita el Modelo
}
