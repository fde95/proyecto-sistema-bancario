package main.com.fespinoza.models;

import main.com.fespinoza.interfaces.IEntidadBancaria;

/**
 * Clase querepresenta una cuenta bancaria en el sistema.
 * Implementa la interfaz IEntidadBancaria.
 */

public class CuentaBancaria implements IEntidadBancaria {

    private String numeroCuenta;
    private String titular;
    private Double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, Double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
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
            System.out.println("El saldo de su cuenta es de: $" + saldo);
        } else {
            System.out.println("Cantidad inválida para depositar.");
        }
    }

    public Boolean retirar(Double cantidad) {
        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro realizado: $" + cantidad);
            System.out.println("El saldo de su cuenta es de: $" + saldo);
            return true;
        }
        System.out.println("Fondos insuficientes o cantidad inválida.");
        return false;
    }
}
