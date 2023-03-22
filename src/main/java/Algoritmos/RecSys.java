package Algoritmos;

import Exceptions.MasDatosQueGruposException;
import Interfaces.Algorithm;
import PR1.Row;
import PR1.Table;

import java.util.ArrayList;
import java.util.List;

public class RecSys {

    Algorithm algorithm ;

    List<String> testItemNames;
    List<Integer> grupos = new ArrayList<>();

    Table testData;






    public RecSys(Algorithm algorithm){
        this.algorithm = algorithm;
    }

    public void train(Table trainData) throws MasDatosQueGruposException {
        algorithm.train(trainData);
    }
    //<>
    public void run(Table testData, List<String> testItemNames){
        this.testItemNames = testItemNames;
        this.testData = testData;
       List<Row> RowList = testData.getInstancias();
       List<Double> datos;
       Integer estimacion;
       for(Row row: RowList){
           datos = row.getData();
           estimacion = (Integer) algorithm.estimate(datos);
           grupos.add(estimacion);
       }

    }

    public List<String> recommend(String nameLikedItem, int numRecommendations){
       // buscamos el grupo del nombre que nos pasan
        int indice = findName(nameLikedItem);
        Integer grupo = grupos.get(indice);

        List<Integer> listaDeIndices = new ArrayList<>();
        //Encontramos los indices de todos los datos que tengan el mismo grupo
        for (int i = 0; i< grupos.size(); i++ ){
            if (grupos.get(i) == grupo && indice != i){
                listaDeIndices.add(i);
                if(listaDeIndices.size() == numRecommendations){
                    break;
                }
            }
        }
        //Asignamos el nombre correspondiente de cada indice
        List<String> recomendaciones = new ArrayList<>();
        for (Integer integer : listaDeIndices){
            recomendaciones.add(testItemNames.get(integer));
        }

        return recomendaciones;
    }

    private int findName( String nombre){
        int i = 0;
        for(String dato: testItemNames){
            if (dato.equals(nombre)){
                return i;
            }
            i++;
        }
        return -1;
    }




}
