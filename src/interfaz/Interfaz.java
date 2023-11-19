package interfaz;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dominio.*;

public class Interfaz implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Vehiculo> catalogo;
    private Scanner sc;

    public Interfaz() {
        catalogo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
    }

    public void iniciarPrograma() {
        cargarDatos();
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearVehiculoTurismo();
                    break;
                case 2:
                    crearFurgoneta();
                    break;
                case 3:
                    mostrarCatalogo();
                    break;
                case 4:
                    mostrarInformacion();
                    break;
                case 5:
                    guardarDatos();
                    System.out.println("Ha terminado.");
                    break;
                case 6:
                    System.out.println("Gracias por utilizar el programa.");
                    break;
                default:
                    System.out.println("no valido. Intente nuevamente.");
                    break;
            }

        } while (opcion != 6);
    }
    

    private void mostrarMenu() {
        System.out.println("MENÚ ");
        System.out.println("1. Crear VehiculoTurismo");
        System.out.println("2. Crear Furgoneta");
        System.out.println("3. Mostrar catalogo");
        System.out.println("4. Mostrar información");
        System.out.println("5. Guardar datos");
        System.out.println("6. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private void crearVehiculoTurismo() {
        System.out.print("\nIngresa la cantidad de Vehiculos Turismo que deseas crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nCREANDO VEHICULO TURISMO " + (i + 1) );
            System.out.print("Ingrese la marca del Vehiculo: ");
            String marca = sc.nextLine();
            System.out.print("Ingrese el modelo del Vehiculo: ");
            String modelo = sc.nextLine();
            System.out.print("Ingrese el numero de Plazas: ");
            int numPlazas = sc.nextInt();
            System.out.print("Ingrese el precio Base: ");
            double precioBase = sc.nextDouble();
            sc.nextLine();

            Vehiculo vehiculoTurismo = new VehiculoTurismo(marca, modelo, numPlazas, precioBase);

            System.out.println("VehiculoTurismo creado: " + vehiculoTurismo);

            agregarVehiculo(vehiculoTurismo);
        }
    }

    private void crearFurgoneta() {
        System.out.print("\nIngrese la cantidad de Furgonetas que desea crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n CREANDO FURGONETA " + (i + 1));
            System.out.print("Ingresa la marca de la Furgoneta: ");
            String marca = sc.nextLine();
            System.out.print("Ingresa el modelo de la Furgoneta: ");
            String modelo = sc.nextLine();
            System.out.print("Ingresa el numero de Plazas: ");
            int numPlazas = sc.nextInt();
            System.out.print("Ingresa el precio Base: ");
            double percioBase = sc.nextDouble();
            System.out.print("Ingresa su capacidad de Carga: ");
            double capacidadCarga = sc.nextDouble();
            sc.nextLine();

            Vehiculo furgoneta = new Furgoneta(marca, modelo, numPlazas, percioBase, capacidadCarga);

            System.out.println("Furgoneta creada: " + furgoneta);

            agregarVehiculo(furgoneta);
        }
    }

    private void mostrarCatalogo() {
        System.out.println("\n CATALOGO DE VEHICULOS ");
        for (Vehiculo vehiculo : catalogo) {
            System.out.println(vehiculo);
        }
    }

    private void mostrarInformacion() {
    if (catalogo.isEmpty()) {
        System.out.println("El catálogo está vacío. No hay información para mostrar.");
        return;
    }

    System.out.print("\nIngrese el índice del vehículo para mostrar información: ");
    int indice = sc.nextInt();
    sc.nextLine();

    if (indice >= 0 && indice < catalogo.size()) {
        Vehiculo vehiculoSeleccionado = catalogo.get(indice);
        System.out.println("\n INFORMACION DEL VEHICULO ");
        System.out.println("Marca: " + vehiculoSeleccionado.getMarca());
        System.out.println("Modelo: " + vehiculoSeleccionado.getModelo());
        System.out.println("Número de Plazas: " + vehiculoSeleccionado.getNumPlazas());
        System.out.println("Precio Base: " + vehiculoSeleccionado.getPrecioBase());
        System.out.println("Precio Final: " + vehiculoSeleccionado.getPrecioFinal());
        if (vehiculoSeleccionado instanceof Furgoneta) {
            Furgoneta furgoneta = (Furgoneta) vehiculoSeleccionado;
            System.out.println("Capacidad de Carga: " + furgoneta.getCapacidadCarga());
        }
    } else {
        System.out.println("Indice no valido. Intente nuevamente.");
    }
}
    private void guardarDatos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("catalogo.csv"))) {
            for (Vehiculo vehiculo : catalogo) {
                if (vehiculo instanceof VehiculoTurismo) {
                    VehiculoTurismo turismo = (VehiculoTurismo) vehiculo;
                    writer.println(String.format("Turismo,%s,%s,%d,%.2f,%.2f", turismo.getMarca(), turismo.getModelo(), turismo.getNumPlazas(), turismo.getPrecioBase(), turismo.calcularPrecioFinal()));
                } else if (vehiculo instanceof Furgoneta) {
                    Furgoneta furgoneta = (Furgoneta) vehiculo;
                    writer.println(String.format("Furgoneta,%s,%s,%d,%.2f,%.2f", furgoneta.getMarca(), furgoneta.getModelo(), furgoneta.getNumPlazas(), furgoneta.getPrecioBase(), furgoneta.calcularPrecioFinal()));
                }
            }
            System.out.println("Datos guardados exitosamente en formato CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos.");
        }
    }

    private void cargarDatos() {
        catalogo.clear();  

        try (BufferedReader reader = new BufferedReader(new FileReader("catalogo.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String tipoVehiculo = data[0];
                    String marca = data[1];
                    String modelo = data[2];
                    int numPlazas = Integer.parseInt(data[3]);
                    double precioBase = Double.parseDouble(data[4]);
                    double precioFinal = Double.parseDouble(data[5]);

                    if ("Turismo".equals(tipoVehiculo)) {
                        VehiculoTurismo turismo = new VehiculoTurismo(marca, modelo, numPlazas, precioBase);
                        turismo.setPrecioFinal(precioFinal);
                        catalogo.add(turismo);
                    } else if ("Furgoneta".equals(tipoVehiculo)) {
                        double capacidadCarga = Double.parseDouble(data[6]);
                        Furgoneta furgoneta = new Furgoneta(marca, modelo, numPlazas, precioBase, capacidadCarga);
                        furgoneta.setPrecioFinal(precioFinal);
                        catalogo.add(furgoneta);
                    }
                }
            }
            System.out.println("Datos cargados exitosamente desde el archivo CSV.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos desde el archivo CSV.");
        }
    }

    private void agregarVehiculo(Vehiculo vehiculo) {
        catalogo.add(vehiculo);
    }
}