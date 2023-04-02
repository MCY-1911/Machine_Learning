package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import Interfaces.Algorithm;
import TratamientoDatos.Filas.Row;
import TratamientoDatos.Tablas.Table;

import java.util.*;

public class Kmeans implements Algorithm<Table ,Integer, List<Double>> {

    private int numClusters; // Cluster = grupo
    private int numIteraciones;
    private long seed;
    private List<Row> representates;

    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters =  numClusters;
        this.numIteraciones = numIterations;
        this.seed = seed;
        representates = new ArrayList<>(numClusters);
    }
    @Override
    public void train(Table datos) throws MasDatosQueGruposException {

        if(numClusters > datos.getNumeroFilas()){
            throw new MasDatosQueGruposException();
        }

        List<Row> centroides = centroidesAleatorios(datos);
        Map<Integer, Set<Integer>> asignaciones = crearEstructuraDatosParaAsignaciones();

        for (int indiceIteracion = 0; indiceIteracion < numIteraciones; indiceIteracion++) {
            // Asignamos cada dato a su centroide más cercano
            for (int indiceFila = 0; indiceFila < datos.getNumeroFilas(); indiceFila++) {
                int centroideMasCercano = centroideMasCercano(centroides, datos.getRowAt(indiceFila).getData());
                asignaciones.get(centroideMasCercano).add(indiceFila);
            }

            // Calculamos los nuevos centroides
            centroides = nuevosCentroides(centroides, asignaciones, datos);
            asignaciones = crearEstructuraDatosParaAsignaciones();

        }
        representates = centroides;

    }

    @Override
    public Integer estimate(List<Double> dato) {
        // El +1 corresponde al que el rango de índices de grupo empieza en 1; (1,...,K)
        return centroideMasCercano(representates, dato) + 1;
    }

    private Map<Integer, Set<Integer>> crearEstructuraDatosParaAsignaciones(){
        // La KEY representará la posición del centroide en la lista "centroides"
        // El VALUE será un conjunto de enteros
        // Cada entero representa la posición de un dato concreto en la tabla
        Map<Integer, Set<Integer>> asignaciones = new HashMap<>();
        for(int i = 0; i < numClusters; i++)
            asignaciones.put(i, new HashSet<>());
        return asignaciones;
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

    private int centroideMasCercano(List<Row> centroides, List<Double> datosFila) {
        // Suponemos que la cantidad de centroides nunca es cero
        // Además los centroides tendrán una distancia muy próxima a la nube de puntos
        double distanciaMin = Double.MAX_VALUE;
        int centroideMasCercano = -1;
        for (int indiceCentroide = 0; indiceCentroide < numClusters; indiceCentroide++) {
            List<Double> datosCentroideActual = centroides.get(indiceCentroide).getData();
            double distancia = Mates.Distancia.getDistanciaEuclidiana(datosFila, datosCentroideActual);
            if (distancia <= distanciaMin) {
                distanciaMin = distancia;
                centroideMasCercano = indiceCentroide;
            }
        }
        return centroideMasCercano;
    }
    //<>
    private List<Row> nuevosCentroides(List<Row> centroidesActuales, Map<Integer, Set<Integer>> asignaciones, Table datos) {

            ArrayList<Row> nuevosCentroides = new ArrayList<>();
            for (int keyCentroide = 0; keyCentroide < centroidesActuales.size(); keyCentroide++) {
                Set<Integer> indicesFilasAsignadas = asignaciones.get(keyCentroide);

                // Creamos un array que representará al nuevo Centroide
                Double[] datosNuevoCentroide = new Double[centroidesActuales.get(keyCentroide).getData().size()];
                Arrays.fill(datosNuevoCentroide, 0.0);

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
