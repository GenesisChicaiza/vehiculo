package dominio;


public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int numPlazas;
    private double precioBase;
    private double precioFinal;


    public Vehiculo(String marca, String modelo, int numPlazas, double precioBase) {
        this.marca = marca;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
        this.precioBase = precioBase;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public int getNumPlazas() {
        return numPlazas;
    }


    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }


    public double getPrecioBase() {
        return precioBase;
    }


    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }


    public double getPrecioFinal() {
        return precioFinal;
    }


    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }


    public abstract double calcularPrecioFinal();
}