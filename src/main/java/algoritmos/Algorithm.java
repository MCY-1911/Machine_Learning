package algoritmos;

import tratamientodatos.tablas.Table;

public interface Algorithm<T extends Table,U,W> {
    void train(T datos) throws MasDatosQueGruposException;
    U estimate(W list);

}
