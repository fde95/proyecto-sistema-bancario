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

    //Métodos publicos: CRUD y operaciones
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
        Usuario usuario = buscarUsuarioInterno(email);
        if (usuario == null) return false;

        if (nuevoNombre != null) usuario.setNombre(nuevoNombre);
        if (nuevoApellido != null) usuario.setApellido(nuevoApellido);
        if (nuevaContrasena != null) usuario.setContrasena(nuevaContrasena);

        System.out.println("Usuario " + email + " actualizado con éxito!");
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
        Usuario usuario = buscarUsuarioInterno(email);
        if (usuario == null) return false;

        if (cuentas.containsKey(numeroCuenta)) {
            System.out.println("Error: La cuenta con número " + numeroCuenta +" ya existe.");
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
        CuentaBancaria cuenta = buscarCuentaInterna(numeroCuenta);
        if (cuenta == null) return false;

        cuentas.remove(numeroCuenta);

        Usuario usuario = buscarUsuarioPorTitular(cuenta.getTitular());
        if (usuario != null) {
            usuario.getCuentas().removeIf(c -> c.getIdentificador().equals(numeroCuenta));
        }

        System.out.println("Cuenta " + numeroCuenta + " eliminada con éxito.");
        return true;
    }

    public boolean transferir(String numeroCuentaOrigen, String numeroCuentaDestino, double monto) {
        CuentaBancaria origen = cuentas.get(numeroCuentaOrigen);
        CuentaBancaria destino = cuentas.get(numeroCuentaDestino);

        if (origen == null || destino == null) return false;

        if (origen.retirar(monto)) {
            destino.depositar(monto);
            System.out.println("$$$ Transferencia de $" + monto + " realizada con éxito. $$$\n");
            return true;
        }

        System.out.println("Error: Transferencia fallida. Fondos insuficientes.");
        return false;
    }

    //Metodos privados: Lógica auxiliar
    private Usuario buscarUsuarioInterno(String email) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            System.out.println("Error: Usuario con email " + email + " no encontrado.");
        }
        return usuario;
    }

    private CuentaBancaria buscarCuentaInterna(String numeroCuenta) {
        CuentaBancaria cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Error: Cuenta con número " + numeroCuenta + " no encontrada.");
        }
        return cuenta;
    }

    private Usuario buscarUsuarioPorTitular(String titular) {
        return usuarios.values().stream()
                .filter(usuario -> usuario.getNombre().equals(titular))
                .findFirst().orElse(null);
    }

}
