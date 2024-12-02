package main.com.fespinoza.exceptions;

/**
 * Clase base para exepciones personalizadas del sistema bancario.
 */

public class BancoException extends Exception {

    public BancoException(String message) {
        super(message); //Pasa el mensaje de error al constuctor de Exeption
    }
}
