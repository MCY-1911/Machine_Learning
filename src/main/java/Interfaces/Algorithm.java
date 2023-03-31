package Interfaces;

import Exceptions.MasDatosQueGruposException;
import TratamientoDatos.Tablas.Table;

public interface Algorithm<T extends Table,U,W> {
    void train(T datos) throws MasDatosQueGruposException;
    U estimate(W list);

}
