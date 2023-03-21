package Interfaces;

import Exceptions.MasDatosQueGruposException;
import PR1.Table;
import PR1.TableWithLabels;

import java.util.List;

public interface Algorithm<T extends Table,U,W> {
    void train(T datos) throws MasDatosQueGruposException;
    U estimate(W list);

}
