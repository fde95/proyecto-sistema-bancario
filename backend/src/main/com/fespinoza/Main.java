package main.com.fespinoza;

import main.com.fespinoza.services.Banco;

public class Main {

    public static void main (String[] args) {
        Banco banco = new Banco();

        // Prueba 1: Crear usuario con datos válidos
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");

        // Prueba 2: Crear usuario con email inválido
        banco.crearUsuario("Juan", "Pérez", "juan.com", "54321");

        // Prueba 3: Crear usuario con campos vacíos
        banco.crearUsuario("", "Pérez", "juan@gmail.com", "54321");

        // Prueba 4: Crear cuenta con saldo inicial válido
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

        // Prueba 5: Crear cuenta con saldo negativo
        banco.crearCuenta("felipe@gmail.com", "002", -500.0);

        // Prueba 6: Transferencia con datos válidos
        banco.crearUsuario("Maria", "Lopez", "maria@gmail.com", "abc123");
        banco.crearCuenta("maria@gmail.com", "003", 500.0);
        banco.transferir("001", "003", 200.0);

        // Prueba 7: Transferencia con saldo insuficiente
        banco.transferir("001", "003", 2000.0);
    }
}
