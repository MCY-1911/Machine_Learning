package mvc.modelo;

import algoritmos.MasDatosQueGruposException;

import java.io.IOException;

public class prueba {
    public static void main(String[] args) throws MasDatosQueGruposException, IOException {
        SongRecomendationApplication impModelo = new SongRecomendationApplication();
        System.out.printf(impModelo.getCanciones().toString());
    }
}
