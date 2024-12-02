package main.com.fespinoza;

import main.com.fespinoza.exceptions.CuentaNoEncontradaException;
import main.com.fespinoza.exceptions.UsuarioNoEncontradoException;
import main.com.fespinoza.services.Banco;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        try {
            // Crear usuarios y cuentas
            banco.crearUsuario("Felipe", "Espinoza", "felipe@gmail.com", "12345");
            banco.crearCuenta("felipe@gmail.com", "001", 1000.0);

            // Intentar buscar una cuenta inexistente
            banco.buscarCuenta("002");
        } catch (CuentaNoEncontradaException e) {
            System.out.println(e.getMessage()); // Debería imprimir: "Cuenta con número '002' no encontrada."
        }

        try {
            // Intentar buscar un usuario inexistente
            banco.buscarUsuario("juan@gmail.com");
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage()); // Debería imprimir: "Usuario con email 'juan@gmail.com' no encontrado."
        }
    }
}
