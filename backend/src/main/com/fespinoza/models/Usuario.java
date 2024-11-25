package main.com.fespinoza.models;

import main.com.fespinoza.interfaces.IEntidadBancaria;

/**
 * Clase que representa a un usuário en el sistema bancario.
 * Implementa la interfaz IEntidadBancaria.
 */

public class Usuario implements IEntidadBancaria {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;

    public Usuario(String nombre, String apellido, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
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
}
