package main.com.fespinoza;

import main.com.fespinoza.exceptions.CuentaNoEncontradaException;
import main.com.fespinoza.services.Banco;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Crear usuarios y cuentas
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

        banco.crearUsuario("Juan", "Pérez", "juan@gmail.com", "54321");
        banco.crearCuenta("juan@gmail.com", "002", 500.0);

        // Realizar transferencia
        banco.transferir("001", "002", 300.0);

        // Manejar la excepción en las búsquedas de cuentas
        try {
            System.out.println("\nHistorial de la cuenta de Felipe:");
            banco.buscarCuenta("001").mostrarHistorial();

            System.out.println("\nHistorial de la cuenta de Juan:");
            banco.buscarCuenta("002").mostrarHistorial();
        } catch (CuentaNoEncontradaException e) {
            System.out.println(e.getMessage()); // Muestra el error si una cuenta no es encontrada
        }
    }
}
