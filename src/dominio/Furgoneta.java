package dominio;

import java.io.Serializable;

public class Furgoneta extends Vehiculo implements Serializable {
    private double capacidadCarga;

    public Furgoneta(String marca, String modelo, int numPlazas, double precioBase, double capacidadCarga) {
        super(marca, modelo, numPlazas, precioBase);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularPrecioFinal() {
        setPrecioFinal(getPrecioBase() * (0.5 * Math.cbrt(capacidadCarga)));
        return getPrecioFinal();
    }

    @Override
public String toString() {
    return "Furgoneta - Marca: " + getMarca() + ", Modelo: " + getModelo() + ", Num Plazas: " + getNumPlazas() + ", Precio Base: " + getPrecioBase() + ", Capacidad de Carga: " + ((Furgoneta) this).getCapacidadCarga() + ", Precio Final: " + calcularPrecioFinal();
}
}