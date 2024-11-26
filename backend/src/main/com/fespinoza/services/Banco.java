package main.com.fespinoza.services;

import main.com.fespinoza.models.CuentaBancaria;
import main.com.fespinoza.models.Usuario;

import java.util.HashMap;
import java.util.Map;

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

    //Método CRUD y operaciones
    public boolean crearUsuario(String nombre, String apellido, String email, String contrasena) {
        if (usuarios.containsKey(email)) {
            System.out.println("\nEl usuario con email " + email + " Ya existe.");
            return false;
        }
        usuarios.put(email, new Usuario(nombre, apellido, email, contrasena));
        System.out.println("====================================\n" +
                "Usuario " + nombre + " " + apellido + " creado con éxito!\n" +
                "====================================\n");
        return true;
    }

    public Usuario buscarUsuario(String email) {
        return usuarios.get(email);
    }

    public boolean actualizarUsuario(String email, String nuevoNombre, String nuevoApellido, String nuevaContrasena) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("\nUsuario no encontrado");
            return false;
        }
        if (nuevoNombre != null) usuario.setNombre(nuevoNombre);
        if (nuevoApellido != null) usuario.setApellido(nuevoApellido);
        if (nuevaContrasena != null) usuario.setContrasena(nuevaContrasena);
        System.out.println("====================================\n" +
                "Usuario " + email + " actualizado con éxito!\n" +
                "====================================\n");
        return true;
    }

    public boolean eliminarUsuario(String email) {
        Usuario usuario = usuarios.remove(email);
        if (usuario == null) {
            System.out.println("\nUsuario no encontrao.");
            return false;
        }
        for (CuentaBancaria cuenta : usuario.getCuentas()) {
            cuentas.remove(cuenta.getIdentificador());
        }
        System.out.println("=== Usuario y cuenta asociadas elimindos con éxito! ===\n");
        return true;
    }

    public boolean crearCuenta(String email, String numeroCuenta, double saldoInicial) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }
        if (cuentas.containsKey(numeroCuenta)) {
            System.out.println("La cuenta con número " + numeroCuenta + " ya existe.\n");
            return false;
        }
        CuentaBancaria cuenta = new CuentaBancaria(numeroCuenta, usuario.getNombre(), saldoInicial);
        cuentas.put(numeroCuenta, cuenta);
        usuario.agregarCuenta(cuenta);
        System.out.println("====================================\n" +
                "Cuenta " + numeroCuenta + " creada con éxito para " + usuario.getNombre() + " " + usuario.getApellido() +
                "\n====================================\n");
        return true;
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    public boolean eliminarCuenta(String numeroCuenta) {
        CuentaBancaria cuenta = cuentas.remove(numeroCuenta);
        if (cuenta == null) {
            System.out.println("\n*** Cuenta no encontrada. ***");
            return false;
        }
        System.out.println("====================================\n" +
                " Cuenta " + numeroCuenta + " eliminada con éxito." +
                "====================================\n");
        return true;
    }

    public boolean transferir(String numeroCuentaOrigen, String numeroCuentaDestino, double monto) {
        CuentaBancaria origen = cuentas.get(numeroCuentaOrigen);
        CuentaBancaria destino = cuentas.get(numeroCuentaDestino);

        if (origen == null || destino == null) {
            System.out.println("Una o ambas cuentas no existen.");
            return false;
        }

        if (origen.retirar(monto)) {
            destino.depositar(monto);
            System.out.println("$$$ Transferencia de $" + monto + " realizada con éxito. $$$\n");
            return true;
        }

        System.out.println("Transferencia fallida. Verifica los fondos de la cuenta origen.");
        return false;
    }
}
