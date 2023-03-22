package Exceptions;

public class MasDatosQueGruposException extends Exception{
    public MasDatosQueGruposException(){
        super("El número de grupos indicado K es mayor que el de datos N");
    }
}
