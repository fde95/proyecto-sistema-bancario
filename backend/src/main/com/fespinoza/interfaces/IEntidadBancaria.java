package main.com.fespinoza.interfaces;

/**
 * Interfaz que define el contrato básico para las entidades bancarias.
 * Todas las clases que implementem esta interfaz deben proporcionar
 * un idetificador único y la capacidad de mostrar información.
 */

public interface IEntidadBancaria {
    /**
     * Devuelve el identificador de la entidad.
     * @return Identificador (por exemplo, email o número de cuente)
     */
    String getIdentificador();

    /**
     * Muestra la información detallada de la entidad.
     */
    void mostrarInformacion();
}
