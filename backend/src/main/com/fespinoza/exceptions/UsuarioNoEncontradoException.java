package main.com.fespinoza.exceptions;

public class UsuarioNoEncontradoException extends BancoException {

  public UsuarioNoEncontradoException(String message) {

    super("Usuário con email '" + message + "' no encontrado.");
  }
}
