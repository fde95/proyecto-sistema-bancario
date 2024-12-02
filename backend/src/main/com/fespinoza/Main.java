package main.com.fespinoza;

import main.com.fespinoza.models.CuentaBancaria;
import main.com.fespinoza.services.Banco;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Crear usuarios y cuentas
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

        banco.crearUsuario("Juan", "Pérez", "juan@gmail.com", "54321");
        banco.crearCuenta("juan@gmail.com", "002", 500.0);

        // Prueba: Transferencia con saldo suficiente
        banco.transferir("001", "002", 300.0);

        // Prueba: Transferencia con saldo insuficiente
        banco.transferir("001", "002", 800.0);

        // Prueba: Intentar retirar una cantidad inválida directamente
        try {
            CuentaBancaria cuenta = banco.buscarCuenta("001");
            cuenta.retirar(-50.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
