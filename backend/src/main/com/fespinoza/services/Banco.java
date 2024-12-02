package main.com.fespinoza.services;

import main.com.fespinoza.exceptions.UsuarioNoEncontradoException;
import main.com.fespinoza.exceptions.CuentaNoEncontradaException;
import main.com.fespinoza.models.CuentaBancaria;
import main.com.fespinoza.models.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Clase que gestiona las operaciones bancarias.
 * Maneja usuarios y cuentas bancarias.
 */
public class Banco {

    private Map<String, Usuario> usuarios;
    private Map<String, CuentaBancaria> cuentas;

    public Banco() {
        this.usuarios = new HashMap<>();
        this.cuentas = new HashMap<>();
    }

    // Métodos públicos: CRUD y operaciones
    public boolean crearUsuario(String nombre, String apellido, String email, String contrasena) {
        if (!validarTexto(nombre, "Nombre") || !validarTexto(apellido, "Apellido") ||
                !validarEmail(email) || !validarTexto(contrasena, "Contraseña")) {
            return false;
        }

        if (usuarios.containsKey(email)) {
            System.out.println("Error: El usuario con email " + email + " ya existe.");
            return false;
        }

        usuarios.put(email, new Usuario(nombre, apellido, email, contrasena));
        System.out.println("Usuario " + nombre + " " + apellido + " creado con éxito!");
        return true;
    }

    public boolean crearCuenta(String email, String numeroCuenta, double saldoInicial) {
        if (!validarTexto(email, "Email") || !validarTexto(numeroCuenta, "Número de Cuenta") ||
                !validarSaldo(saldoInicial)) {
            return false;
        }

        try {
            Usuario usuario = buscarUsuarioInterno(email);

            if (cuentas.containsKey(numeroCuenta)) {
                System.out.println("Error: La cuenta con número " + numeroCuenta + " ya existe.");
                return false;
            }

            CuentaBancaria cuenta = new CuentaBancaria(numeroCuenta, usuario.getNombre(), saldoInicial);
            cuentas.put(numeroCuenta, cuenta);
            usuario.agregarCuenta(cuenta);

            System.out.println("Cuenta " + numeroCuenta + " creada con éxito para " + usuario.getNombre());
            return true;

        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean transferir(String numeroCuentaOrigen, String numeroCuentaDestino, double monto) {
        if (!validarTexto(numeroCuentaOrigen, "Número de Cuenta Origen") ||
                !validarTexto(numeroCuentaDestino, "Número de Cuenta Destino") || !validarSaldo(monto)) {
            return false;
        }

        try {
            CuentaBancaria origen = buscarCuentaInterna(numeroCuentaOrigen);
            CuentaBancaria destino = buscarCuentaInterna(numeroCuentaDestino);

            if (origen.retirar(monto)) {
                destino.depositar(monto);
                System.out.println("Transferencia de $" + monto + " realizada con éxito.");
                return true;
            }

            System.out.println("Error: Transferencia fallida. Fondos insuficientes.");
            return false;

        } catch (CuentaNoEncontradaException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Métodos privados: Validaciones
    private boolean validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Error: El campo " + campo + " no puede estar vacío.");
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Error: El email no puede estar vacío.");
            return false;
        }

        // Validar formato de email
        String regex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(regex, email)) {
            System.out.println("Error: El email " + email + " tiene un formato inválido.");
            return false;
        }
        return true;
    }

    private boolean validarSaldo(double saldo) {
        if (saldo < 0) {
            System.out.println("Error: El saldo no puede ser negativo.");
            return false;
        }
        return true;
    }

    // Métodos auxiliares de búsqueda (ahora lanzan excepciones)
    private Usuario buscarUsuarioInterno(String email) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException(email);
        }
        return usuario;
    }

    private CuentaBancaria buscarCuentaInterna(String numeroCuenta) throws CuentaNoEncontradaException {
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException(numeroCuenta);
        }
        return cuenta;
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) throws CuentaNoEncontradaException {
        return buscarCuentaInterna(numeroCuenta);
    }

    public Usuario buscarUsuario(String email) throws UsuarioNoEncontradoException {
        return buscarUsuarioInterno(email);
    }
}
