package Interfaces;


import Exceptions.PuntosDiferentesDimensiones;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q) throws PuntosDiferentesDimensiones;
}
