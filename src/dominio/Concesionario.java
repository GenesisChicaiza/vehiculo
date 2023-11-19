package dominio;
import java.util.ArrayList;

public class Concesionario {
    private ArrayList<Vehiculo> catalogo;

    public Concesionario() {
        catalogo = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getCatalogo() {
        return catalogo;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        catalogo.add(vehiculo);
    }

    public void mostrarCatalogo() {
        for (Vehiculo vehiculo : catalogo) {
            System.out.println(
                "Marca: " + vehiculo.getMarca() +
                "Modelo: " + vehiculo.getModelo() +
                "Numero de Plazas: " + vehiculo.getNumPlazas() +
                "Precio Base: " + vehiculo.getPrecioBase() +
                "Precio final: " + vehiculo.calcularPrecioFinal()
            );
        }
    }
}