package PR1;

public class RowWithLabel extends Row{
    protected int numberClass;

    public RowWithLabel() {
        super();
    }
    public RowWithLabel(Double[] fila, int numeroClase){
        super(fila);
        numberClass = numeroClase;
    }

    public int getNumbreClass(){
        return numberClass;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder(super.toString());
        resultado.append(numberClass);
        return resultado.toString();
    }
}
