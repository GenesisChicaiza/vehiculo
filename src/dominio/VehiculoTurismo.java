package dominio;
import java.io.Serializable;

public class VehiculoTurismo extends Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;


    public VehiculoTurismo(String marca, String modelo,int numPlazas, double precioBase){
        super(marca, modelo, numPlazas, precioBase);
    }
    
    @Override
    public double calcularPrecioFinal() {
        if (getNumPlazas() <= 5) {
            setPrecioFinal(getPrecioBase());
        } else {
            setPrecioFinal(getPrecioBase() + getPrecioBase() * 0.10);
        }
        return getPrecioFinal();
    }
    @Override
public String toString() {
    return "VehiculoTurismo - Marca: " + getMarca() + ", Modelo: " + getModelo() + ", Num Plazas: " + getNumPlazas() + ", Precio Base: " + getPrecioBase() + ", Precio Final: " + calcularPrecioFinal();
}


}