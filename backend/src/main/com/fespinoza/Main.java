package main.com.fespinoza;

import main.com.fespinoza.services.Banco;

public class Main {

    public static void main (String[] args) {
        Banco banco = new Banco();

        // Crear usuarios
        banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
        banco.crearUsuario("Juan", "Pérez", "juan@gmail.com", "54321");

        // Crear cuentas
        banco.crearCuenta("felipe@gmail.com", "001", 1000.0);
        banco.crearCuenta("juan@gmail.com", "002", 500.0);

        // Transferir dinero
        banco.transferir("001", "002", 200.0);

        // Buscar cuentas
        banco.buscarCuenta("001").mostrarInformacion();
        banco.buscarCuenta("002").mostrarInformacion();

        // Eliminar usuario
        banco.eliminarUsuario("juan@gmail.com");

        // Verificar que las cuentas de Juan fueron eliminadas
        banco.buscarCuenta("002");
    }
}
