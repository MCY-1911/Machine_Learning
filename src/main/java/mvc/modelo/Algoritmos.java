package mvc.modelo;

public enum Algoritmos {
    KNN("knn"),
    KMEANS("kmeans");

    private String descripcion;

    private Algoritmos(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
