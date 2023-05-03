package mvc.modelo;

public enum Distancias {
    EUCLIDEAN("euclidean"),
    MANHATTAN("manhattan");

    private String descripcion;

    private Distancias(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
