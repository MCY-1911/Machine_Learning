package algoritmos;

import mates.PuntosDiferentesDimensiones;
import mates.Distance;
import tratamientoDatos.filas.RowWithLabel;
import tratamientoDatos.tablas.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels,Integer, List<Double>>, DistanceClient {

    TableWithLabels muestra;
    Distance distance;

    public KNN(Distance distancia){
        super();
        muestra = new TableWithLabels();
        distance = distancia;
    }



    @Override
    public Integer estimate(List<Double> data) {

        double distanciaMin = Double.MAX_VALUE;
        int numeroClaseEstimado = 0;
        for(int indiceTabla = 0; indiceTabla < muestra.getNumeroFilas(); indiceTabla++) {
            RowWithLabel candidato = muestra.getRowAt(indiceTabla);
            Double distancia = null;
            try {
                distancia = distance.calculateDistance(data, candidato.getData());
            } catch (PuntosDiferentesDimensiones e) {
                e.printStackTrace();
            }
            if (distancia < distanciaMin) {
                distanciaMin = distancia;
                numeroClaseEstimado = candidato.getNumberClass();
            }
        }

        return numeroClaseEstimado;
    }

    @Override
    public void train(TableWithLabels datos) {
        muestra = datos;
    }

    @Override
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
