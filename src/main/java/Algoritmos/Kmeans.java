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

        // Estructuras de datos que emplearemos en el algoritmo
        List<Row> centroides = centroidesAleatorios(datos);
        Map<Integer, Set<Integer>> asignaciones = new HashMap<>();
        // La KEY representará la posición del centroide en la lista
        // El VALUE será un conjunto de las posiciones de los datos en la tabla
        for(int i = 0; i < centroides.size(); i++)
            asignaciones.put(i, new HashSet<>());

        //
        for (int indiceIteracion = 0; indiceIteracion < numIteraciones; indiceIteracion++) {
            // Asignamos cada dato a su centroide más cercano
            for (int indiceFila = 0; indiceFila < datos.getNumeroFilas(); indiceFila++) {
                int centroideMasCercano = centroideMasCercano(centroides, datos.getRowAt(indiceFila).getData());
                asignaciones.get(centroideMasCercano).add(indiceFila);
            }
            // Calculamos los nuevos centroides
            centroides = nuevosCentroides(centroides, asignaciones, datos);
            asignaciones = new HashMap<>();
            for(int i = 0; i < centroides.size(); i++)
                asignaciones.put(i, new HashSet<>());

        }
    }

    public Integer estimate(List<Double> dato) {
        return 0;
    }

    private List<Row> centroidesAleatorios(Table datos) {

        Random random = new Random(seed);
        ArrayList<Row> centroidesEscogidos = new ArrayList<>();
        int indiceCentroideActual;

        for (int i = 0; i < numClusters; i++) {
            // Generamos aleatoriamente índices dentro del rango de filas de la tabla
            // Si una fila ya la hemos tomado como Centroide. Generamos otro índice
            do {
                indiceCentroideActual = random.nextInt(datos.getNumeroFilas());
            } while (centroidesEscogidos.contains(datos.getRowAt(indiceCentroideActual)));
            // Una vez nos hemos asegurado de que es un Centroide nuevo, lo añadimos a la lista
            centroidesEscogidos.add(datos.getRowAt(indiceCentroideActual));
        }
        return centroidesEscogidos;
    }

    private double getDistanciaEuclidiana(List<Double> vector1, List<Double> vector2) {
        // Supondremos que ambos vectores tienen el mismo tamaño
        double distancia = 0.0;
        for (int i = 0; i < vector1.size(); i++) {
            distancia += Math.pow(vector1.get(i)- vector2.get(i), 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }

    private int centroideMasCercano(List<Row> centroides, List<Double> fila) {
        // Suponemos que la cantidad de centroides nunca es cero
        double distanciaMin = Double.MAX_VALUE;
        int centroideMasCercano = -1;
        for (int indiceCentroide = 0; indiceCentroide < centroides.size(); indiceCentroide++) {
            List<Double> datosCentroideActual = centroides.get(indiceCentroide).getData();
            double distancia = getDistanciaEuclidiana(fila, datosCentroideActual);
            if (distancia <= distanciaMin) {
                distanciaMin = distancia;
                centroideMasCercano = indiceCentroide;
            }
        }
        return centroideMasCercano;
    }

    private List<Row> nuevosCentroides(List<Row> centroidesActuales, Map<Integer, Set<Integer>> asignaciones, Table datos) {

            ArrayList<Row> nuevosCentroides = new ArrayList<>();

            for (int keyCentroide = 0; keyCentroide < centroidesActuales.size(); keyCentroide++) {
                Set<Integer> indicesFilasAsignadas = asignaciones.get(keyCentroide);
                // Creamos un array que representará al nuevo Centroide
                Double[] datosNuevoCentroide = new Double[centroidesActuales.get(keyCentroide).getData().size()];
                for(int indiceFila: indicesFilasAsignadas) {
                    List<Double> datosFila = datos.getRowAt(indiceFila).getData();
                    for (int atributo = 0; atributo < datosNuevoCentroide.length; atributo++) {
                        datosNuevoCentroide[atributo] += datosFila.get(atributo);
                    }
                }
                // Dividimos por la cantidad de filas que representa el nuevo centroide
                for (int i = 0; i < datosNuevoCentroide.length; i++) {
                    datosNuevoCentroide[i] /= asignaciones.get(keyCentroide).size();
                }
                // Añadimos el nuevo centroide a la lista
                nuevosCentroides.add(new Row(datosNuevoCentroide));
            }
            return nuevosCentroides;
    }

}
