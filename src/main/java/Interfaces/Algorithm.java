package Interfaces;

import PR1.Table;
import PR1.TableWithLabels;

import java.util.List;

public interface Algorithm<T extends Table,U,W> {
    void train(T datos);
    U estimate(W list);

}
