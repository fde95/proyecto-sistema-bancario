package main.com.fespinoza.models;

import main.com.fespinoza.exceptions.SaldoInsuficienteException;
import main.com.fespinoza.interfaces.IEntidadBancaria;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase querepresenta una cuenta bancaria en el sistema.
 * Implementa la interfaz IEntidadBancaria.
 */

public class CuentaBancaria implements IEntidadBancaria {

    private String numeroCuenta;
    private String titular;
    private Double saldo;
    private List<Transaccion> historial;

    public CuentaBancaria(String numeroCuenta, String titular, Double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    @Override
    public String getIdentificador() {
        return numeroCuenta; //El identificador único para cuentas es el número de cuenta.
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("===== Información de la Cuenta =====");
        System.out.println("Nº de Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: $" + saldo);
        System.out.println("====================================\n");
    }

    public void depositar(Double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Deposito realizado: $" + cantidad);
            System.out.println("El saldo de su cuenta es de: $" + saldo + "\n");
            agregarTransaccion(new Transaccion(Transaccion.Tipo.DEPOSITO, cantidad,"Depósito en cuenta"));
        } else {
            System.out.println("Cantidad inválida para depositar.");
        }
    }

    public void retirar(Double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0 || saldo < cantidad) {
            throw new SaldoInsuficienteException(saldo, cantidad);
        }
        saldo -= cantidad;
        System.out.println("Retiro realizado: $" + cantidad);
        agregarTransaccion(new Transaccion(Transaccion.Tipo.RETIRO, cantidad, "Retiro de cuenta."));
    }

    public void agregarTransaccion(Transaccion transaccion) {
        historial.add(transaccion);
    }

    public void mostrarHistorial() {
        System.out.println("\n=== Historial de transacciones para la cuenta " + numeroCuenta + " ===");
        for (Transaccion transaccion : historial) {
            System.out.println(transaccion.getDetalle());
        }
        System.out.println("=======================================\n");
    }

    //Getter and Setter
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
