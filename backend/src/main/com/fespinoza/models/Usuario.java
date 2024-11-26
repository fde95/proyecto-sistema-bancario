package main.com.fespinoza.models;

import main.com.fespinoza.interfaces.IEntidadBancaria;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuário en el sistema bancario.
 * Implementa la interfaz IEntidadBancaria.
 */

public class Usuario implements IEntidadBancaria {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private List<CuentaBancaria> cuentas;

    public Usuario(String nombre, String apellido, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.cuentas = new ArrayList<>(); //
    }

    @Override
    public String getIdentificador() {
        return email; //El identificador único para usuario es su email
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("===== Información del Usuário =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("====================================\n");

    }

    public Boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
