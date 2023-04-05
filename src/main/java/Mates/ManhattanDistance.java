package Mates;

import Exceptions.PuntosDiferentesDimensiones;
import Interfaces.Distance;

import java.util.List;

public class ManhattanDistance implements Distance {
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) throws PuntosDiferentesDimensiones {
        if (p.size()!= q.size())
            throw new PuntosDiferentesDimensiones();
        // Supondremos que ambos vectores tienen el mismo tamaño
        double distancia = 0.0;
        for (int i = 0; i < p.size(); i++)
            distancia += Math.abs(p.get(i) - q.get(i));
        return distancia;
    }
}
