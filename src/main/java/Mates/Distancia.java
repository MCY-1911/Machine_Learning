package Mates;

import java.util.List;

public class Distancia {
    public static double getDistanciaEuclidiana(List<Double> vector1, List<Double> vector2) {
        // Supondremos que ambos vectores tienen el mismo tamaño
        double distancia = 0.0;
        for (int i = 0; i < vector1.size(); i++)
            distancia += Math.pow(vector1.get(i)-vector2.get(i), 2);
        distancia = Math.sqrt(distancia);
        return distancia;
    }
}
