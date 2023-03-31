package Algoritmos;

import Interfaces.Algorithm;
import TratamientoDatos.Filas.RowWithLabel;
import TratamientoDatos.Tablas.TableWithLabels;

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
            Double distancia = Mates.Distancia.getDistanciaEuclidiana(data, candidato.getData());
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
}
