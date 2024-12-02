package main.com.fespinoza.models;

import main.com.fespinoza.exceptions.SaldoInsuficienteException;
import main.com.fespinoza.interfaces.IEntidadBancaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una cuenta bancaria en el sistema.
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
        return numeroCuenta; // El identificador único para cuentas es el número de cuenta.
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
            System.out.println("Depósito realizado: $" + cantidad);
            System.out.println("El saldo de su cuenta es de: $" + saldo + "\n");
            agregarTransaccion(new Transaccion(Transaccion.Tipo.DEPOSITO, cantidad, "Depósito en cuenta."));
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

    /**
     * Filtrar las transacciones por tipo.
     *
     * @param tipo El tipo de transacción a filtrar (DEPOSITO, RETIRO, TRANSFERENCIA).
     * @return Una lista con las transacciones del tipo especificado.
     */
    public List<Transaccion> filtrarPorTipo(Transaccion.Tipo tipo) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : historial) {
            if (transaccion.getTipo() == tipo) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * Filtrar las transacciones dentro de un rango de fechas.
     *
     * @param inicio Fecha de inicio del rango.
     * @param fin    Fecha de fin del rango.
     * @return Una lista con las transacciones dentro del rango de fechas.
     */
    public List<Transaccion> filtrarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : historial) {
            if (transaccion.getFecha().isAfter(inicio) && transaccion.getFecha().isBefore(fin)) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * Mostrar una lista de transacciones filtradas.
     *
     * @param transacciones Lista de transacciones a mostrar.
     */
    public void mostrarTransaccionesFiltradas(List<Transaccion> transacciones) {
        System.out.println("=== Transacciones filtradas para la cuenta " + numeroCuenta + " ===");
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion.getDetalle());
        }
        System.out.println("=======================================");
    }

    // Getters y setters
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
