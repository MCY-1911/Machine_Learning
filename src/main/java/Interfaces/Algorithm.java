package Interfaces;

import PR1.Table;

import java.util.List;

public interface Algorithm<T extends Table, U, W> {
    void train(Table datos);
    U estimate(W dato);
}
