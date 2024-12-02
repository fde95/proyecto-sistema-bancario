package main.com.fespinoza;

import main.com.fespinoza.exceptions.CuentaNoEncontradaException;
import main.com.fespinoza.exceptions.SaldoInsuficienteException;
import main.com.fespinoza.models.Transaccion;
import main.com.fespinoza.services.Banco;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Crear usuarios y cuentas
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

        banco.crearUsuario("Juan", "Pérez", "juan@gmail.com", "54321");
        banco.crearCuenta("juan@gmail.com", "002", 500.0);

        try {
            // Realizar operaciones
            banco.transferir("001", "002", 300.0);
            banco.buscarCuenta("001").depositar(200.0);
            banco.buscarCuenta("001").retirar(100.0);

            // Mostrar todas las transacciones
            System.out.println("\n=== Todas las transacciones ===");
            banco.buscarCuenta("001").mostrarHistorial();

            // Filtrar por tipo: TRANSFERENCIA
            System.out.println("\n=== Filtrar por tipo: TRANSFERENCIA ===");
            banco.buscarCuenta("001").mostrarTransaccionesFiltradas(
                    banco.buscarCuenta("001").filtrarPorTipo(Transaccion.Tipo.TRANSFERENCIA)
            );

            // Filtrar por rango de fechas (últimas 24 horas)
            LocalDateTime hace24Horas = LocalDateTime.now().minusHours(24);
            LocalDateTime ahora = LocalDateTime.now();

            System.out.println("\n=== Filtrar por rango de fechas (últimas 24 horas) ===");
            banco.buscarCuenta("001").mostrarTransaccionesFiltradas(
                    banco.buscarCuenta("001").filtrarPorRangoDeFechas(hace24Horas, ahora)
            );

        } catch (CuentaNoEncontradaException | SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
}
