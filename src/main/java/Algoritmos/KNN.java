package Algoritmos;

import Interfaces.Algorithm;
import PR1.*;
import java.util.List;

public class KNN implements Algorithm<TableWithLabels,Integer, List<Double>> {

    TableWithLabels muestra;

    public KNN(){
        super();
        muestra = new TableWithLabels();
    }


    @Override
    public Integer estimate(List<Double> data) {

        double distanciaMin = Double.MAX_VALUE;
        int numeroClaseEstimado = 0;
        for(int indiceTabla = 0; indiceTabla < muestra.getNumeroFilas(); indiceTabla++) {
            RowWithLabel candidato = muestra.getRowAt(indiceTabla);
            Double distancia = getDistancia(data, candidato);
            if (distancia < distanciaMin) {
                distanciaMin = distancia;
                numeroClaseEstimado = candidato.getNumberClass();
            }
        }

        return numeroClaseEstimado;
    }

    public Double getDistancia(List<Double> ejemplar, RowWithLabel candidato) {
        double distancia = 0.0;
        for (int posAtributo = 0; posAtributo < ejemplar.size(); posAtributo++) {
            distancia += Math.pow((ejemplar.get(posAtributo) - candidato.getData().get(posAtributo)), 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }

    @Override
    public void train(TableWithLabels datos) {

    }
}
