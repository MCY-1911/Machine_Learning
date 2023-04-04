package Exceptions;

public class PuntosDiferentesDimensiones extends Exception{
    public PuntosDiferentesDimensiones() { super("Los dos puntos no tienen la misma cantidad de componentes en sus coordenadas"); }
}
