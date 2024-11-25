package main.com.fespinoza;

import main.com.fespinoza.interfaces.IEntidadBancaria;
import main.com.fespinoza.models.CuentaBancaria;
import main.com.fespinoza.models.Usuario;

public class Main {

    public static void main (String[] args) {
        //crear un usuario
        Usuario usuario = new Usuario("Felipe", "Espinoza", "felipe@gmail.com", "123456");

        //crear una cuenta bancaria
        CuentaBancaria cuenta = new CuentaBancaria("001", "Felipe Espinoza", 1000.0);

        //Polimorfismo: trata ambos como IEntidadBancaria.
        IEntidadBancaria entidadUsuario = usuario;
        IEntidadBancaria entidadCuenta = cuenta;

        //Mostrar información usando los metodos de la interfaz
        entidadUsuario.mostrarInformacion();
        entidadCuenta.mostrarInformacion();
    }
}
