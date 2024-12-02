package main.com.fespinoza;

import main.com.fespinoza.exceptions.SaldoInsuficienteException;
import main.com.fespinoza.services.Banco;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Crear usuario y cuenta
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

        try {
            // Realizar operaciones
            banco.buscarCuenta("001").depositar(200.0);
            banco.buscarCuenta("001").retirar(150.0);

            // Mostrar historial
            banco.buscarCuenta("001").mostrarHistorial();
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
