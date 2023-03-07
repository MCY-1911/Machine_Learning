package Algoritmos;

import PR1.Row;
import PR1.Table;

import java.util.*;

public class Kmeans {

    private int numClusters; // Cluster = grupo
    private int numIteraciones;
    private long seed;

    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters =  numClusters;
        this.numIteraciones = numIterations;
        this.seed = seed;
    }

    public void train(Table datos) {

        // Establecemos la semilla (necesario para que todas las pruebas se ejecuten igual)
        Random random = new Random(seed);

        // Creamos un mapa para asignar a cada centroide sus datos más cercanos.
        // La KEY será un Row que represente el centroide
        // El VALUE será un SET de INTEGER que representarás la posición de cada dato en la tabla
        // El número que representa el centroide corresponderá con su posición en la lista
        Map<Row, Set<Integer>> asignaciones = new HashMap<>();
        ArrayList<Row> CentroidesOrdenados = new ArrayList<>();

        for (int i = 0; i < numClusters; i++) {
            int posCentroideTabla = random.nextInt(datos.getNumeroFilas());
            // Para escoger centroides diferentes
            while(asignaciones.containsKey(posCentroideTabla))
                posCentroideTabla = random.nextInt(datos.getNumeroFilas());

            // Añadimos el centroide a las estructuras de datos correspondientes
            Row centroide = datos.getRowAt(posCentroideTabla);
            CentroidesOrdenados.add(centroide);
            asignaciones.put(centroide, new HashSet<>());
        }

        // Repetimos el algoritmo numIteraciones veces
        for(int i = 0; i < numIteraciones ; i++) {
            // Asignación de los datos a
            for(Row instancia: datos.getInstancias()) {
                double distanciaMin = Double.MAX_VALUE;
                int posCentroideMasCercano = -1;
                double distancia = 0.0;
                List<Double> coordenadasDato = instancia.getData();
                for(Row centroide : CentroidesOrdenados) {
                    List<Double> coordenadasCentroide = centroide.getData();
                    for(int posCoordenada = 0; posCoordenada < coordenadasDato.size() ; posCoordenada++) {
                        distancia = Math.pow(coordenadasCentroide.get(posCoordenada) - coordenadasDato.get(posCoordenada), 2);
                    }
                    distancia = Math.sqrt(distancia);
                    if(distancia < distanciaMin) {
                        distanciaMin = distancia;
                        posCentroideMasCercano = CentroidesOrdenados.indexOf(centroide);
                    }
                }
                asignaciones.get(CentroidesOrdenados.get(posCentroideMasCercano)).add(datos.getPoicionFila(instancia));
            }
            // Cálculo de los nuevos centroides
            ArrayList<Row> nuevosCentroides = new ArrayList<>();
            for(Row centroide: asignaciones.keySet()) {
                List<Double> sumatorios = new ArrayList<>();
                for(int j = 0; j < centroide.getData().size(); j++)
                    sumatorios.add(0.0);
                Set<Integer> grupo = asignaciones.get(centroide);
                for(Integer posFila: grupo) {
                    List<Double> coordenadas = datos.getRowAt(posFila).getData();
                    for(int j = 0; j < sumatorios.size() ; j++)
                        sumatorios.set(j, coordenadas.get(j) + sumatorios.get(j));

                }
                for(int j = 0; j < centroide.getData().size(); j++)
                    sumatorios.set(j, sumatorios.get(j) / grupo.size());
                Double[] array = new Double[sumatorios.size()];
                Row nuevoCentroide = new Row(sumatorios.toArray(array));
                nuevosCentroides.add(nuevoCentroide);
                asignaciones.put(nuevoCentroide, new HashSet<>());
            }
        }

    }

    public Integer estimate(List<Double> dato) {
        return 0;
    }

}