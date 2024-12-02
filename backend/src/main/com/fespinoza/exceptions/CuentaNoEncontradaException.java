package main.com.fespinoza.exceptions;

public class CuentaNoEncontradaException extends BancoException {

    public CuentaNoEncontradaException(String message) {

      super("Cuenta con número '" + message + "' no encontrada.");
    }
}
