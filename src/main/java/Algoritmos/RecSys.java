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
    List<Integer> identificadorGrupoDeCadaTitulo; // "Columna" de labels. El titulo i-ésimo de la tabla tendrá
                                                  // por identificador el Integer que ocupe la posición
                                                  //  i-ésima de la lista
    Table tablaDatosTitulos; // Contiene datos numéricos para caracterizar a cada titulo

    public RecSys(Algorithm algorithm){
        this.algoritmo = algorithm;
        this.identificadorGrupoDeCadaTitulo = new ArrayList<>();
    }

    public void train(Table trainData) throws MasDatosQueGruposException {
        algoritmo.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames){
        // Este método simplemente debe etiquetar los datos. Es decir,
        // que las canciones, por ejemplo, que son similares entre sí pertenezcan al mismo grupo.
        // Los datos de cada canción puede ser su duración, sus likes, su velocidad, etc.

        // testItemNames simplemente contiene los títulos de las canciones.
        this.titulos = testItemNames;
        this.tablaDatosTitulos = testData;

        // Método auxiliar, estima el grupo de TODOS los datos
        this.estimate();

    }

    public List<String> recommend(String nameLikedItem, int numRecommendations){

        // Paso 1: obtenemos los datos del título marcado como Liked
        int indiceLiked = findName(nameLikedItem);

        // Paso 2: obtenemos el identificador del grupo de dicha canción Liked
        Integer labelLiked = identificadorGrupoDeCadaTitulo.get(indiceLiked);

        // Paso 3: localizar títulos del grupo
        List<Integer> listaDeIndices = selectItems(indiceLiked, labelLiked, numRecommendations);

        //Asignamos el nombre correspondiente de cada indice y devolvemos
        return getNamesSelectedItems();
    }

    private int findName(String nombre){
        return titulos.indexOf(nombre);
    }

    private void estimate(){
        // OJO, no sé el cast a Integer si es correcto. PREGUNTAR PROFE !!!!!!!!!!!!
        for(Row datosTitulo:  tablaDatosTitulos.getInstancias())
            identificadorGrupoDeCadaTitulo.add((Integer) algoritmo.estimate(datosTitulo.getData()));
    }

    private List<Integer> selectItems(int indiceLiked, int labelLiked, int numRecomendaciones){
        List<Integer> listaDeIndices = new ArrayList<>();
        for (int indiceCandidato = 0; indiceCandidato < identificadorGrupoDeCadaTitulo.size(); indiceCandidato++) {
            Integer labelCandidato = identificadorGrupoDeCadaTitulo.get(indiceCandidato);
            if (labelCandidato == labelLiked && indiceCandidato != indiceLiked)
                listaDeIndices.add(indiceCandidato);
            if (listaDeIndices.size() >= numRecomendaciones)
                break;
        }
        return listaDeIndices;
    }

    private List<String> getNamesSelectedItems(List<Integer> listaIndices) {
        List<String> listaDeNombres = new ArrayList<>(listaIndices.size());
        for (Integer indice : listaIndices)
            listaDeNombres.add(titulos.get(indice));
        return listaDeNombres;
    }


}
