package main.com.fespinoza.exceptions;

public class SaldoInsuficienteException extends BancoException {

    public SaldoInsuficienteException(double saldoActual, double montoRequerido) {

        super("Saldo insuficiente: Disponible $" + saldoActual + ", se requiere $" + montoRequerido);
    }
}
