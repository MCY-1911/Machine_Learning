package PR1;

public class RowWithLabel extends Row{
    protected int numberClass;

    RowWithLabel() {
        super();
    }
    RowWithLabel(Double[] fila, int numeroClase){
        super();
        numberClass = numeroClase;
    }

    public int getNumbreClass(){
        return numberClass;
    }
}
