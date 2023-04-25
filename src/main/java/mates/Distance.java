package mates;


import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q) throws PuntosDiferentesDimensiones;
}
