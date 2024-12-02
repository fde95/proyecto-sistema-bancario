package main.com.fespinoza.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una transacción bancaria.
 */

public class Transaccion {

    public enum Tipo {
        DEPOSITO, RETIRO, TRANSFERENCIA
    }

    private Tipo tipo;
    private double monto;
    private LocalDateTime fecha;
    private  String descripcion;

    public Transaccion(Tipo tipo, double monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fecha.format(formatter) + "] " + tipo + ": $" + monto + " - " + descripcion;
    }

    //Getters
    public Tipo getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
