package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import Interfaces.Algorithm;
import PR1.Row;
import PR1.Table;

import java.util.ArrayList;
import java.util.List;

public class RecSys {

    Algorithm algoritmo; // Patrón de diseño: Estrategia
    List<String> titulos; // Títulos de las canciones, películas, videojuegos, etc. de nuestra plataforma
    List<Integer> grupoDeCadaTitulo;
    Table TablaDatosTítulos; // Contiene datos numéricos para caracterizar a cada titulo

    public RecSys(Algorithm algorithm){
        this.algoritmo = algorithm;
        this.grupoDeCadaTitulo = new ArrayList<>();
    }

    public void train(Table trainData) throws MasDatosQueGruposException {
        algoritmo.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames){
        // Este método simplemente debe etiquetar los datos.
        // Por ejemplo, las canciones. testData será una tabla donde cada fila representa una canción
        // Los datos de cada canción puede ser su duración, sus likes, su velocidad, etc.
        // testItemNames simplemente contiene los títulos de las canciones.
        // Vale, cuando llamamos a este método lo que queremos conseguir es que las canciones se etiqueten. Es decir,
        // que las canciones que son similares entre sí pertenezcan al mismo grupo y,
        // por lo tanto, tengan la misma estimación.
        this.titulos = testItemNames;
        this.TablaDatosTítulos = testData;
        // Esto sí son atributos de nuestra plataforma, ahora iniciada, debemos estimar el "grupo" de cada canción
        // OJO, no sé el cast a Integer si es correcto. PREGUNTAR PROFE !!!!!!!!!!!!
        for(Row datosTitulo : testData.getInstancias()) {
            grupoDeCadaTitulo.add((Integer) algoritmo.estimate(datosTitulo.getData()));
        }

    }

    public List<String> recommend(String nameLikedItem, int numRecommendations){
        // Paso 1: obtenemos los datos del título marcado como Liked
        int indice = findName(nameLikedItem);
        List<Double> datosTitulo = TablaDatosTítulos.getRowAt(indice).getData();

        // Paso 2: obtenemos el identificador del grupo de dicha canción Liked
        Integer grupo = grupoDeCadaTitulo.get(indice);

        // Paso 3: localizar titulos del grupo HE LLEGADO HASTA AQUÍ
        List<Integer> listaDeIndices = new ArrayList<>();
        int i = 0;
        while(i < TablaDatosTítulos.getNumeroFilas() && listaDeIndices.size() < numRecommendations) {


            i++;
        }
        //Encontramos los indices de todos los datos que tengan el mismo grupo
        for (int i = 0; i< grupoDeCadaTitulo.size(); i++ ){
            if (grupoDeCadaTitulo.get(i) == grupo && indice != i){
                listaDeIndices.add(i);
                if(listaDeIndices.size() == numRecommendations){
                    break;
                }
            }
        }
        //Asignamos el nombre correspondiente de cada indice
        List<String> recomendaciones = new ArrayList<>();
        for (Integer integer : listaDeIndices){
            recomendaciones.add(titulos.get(integer));
        }

        return recomendaciones;
    }

    private int findName(String nombre){
        return titulos.indexOf(nombre);
    }




}
