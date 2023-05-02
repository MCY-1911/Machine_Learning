package mvc.vista;

import java.util.List;

public interface Vista {
    //Métodos extra para la aplicación
    void crearGUI();
    //Métodos que necesita el Controlador
    int getAlgoritmo();
    int getDistancia();
    String getSong();
    int getNumeroRecomendaciones();
    void muestraRecomendaciones(List<String> recomendaciones);
    //Métodos que necesita el Modelo
}
