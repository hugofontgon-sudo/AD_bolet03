package com.hugofont.AD.tema01.parsers.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MainParsers {

    private MainParsers() { }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   EJECUCIÓN - BOLETÍN 3: PÁRSERS DOM XML");
        System.out.println("==========================================");
        System.out.println("1. Listado simple de empleados (Ejercicio 1)");
        System.out.println("2. Estadísticas por departamento (Ejercicio 2)");
        System.out.println("3. Biblioteca: Consultas y filtrado (Ejercicio 3)");
        System.out.println("4. Pedidos: Consulta y validación (Ejercicio 4)");
        System.out.print("\nElige una opción (1-4): ");

        int opcion = -1;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
        }
        scanner.nextLine(); // Limpiar buffer de salto de línea

        System.out.println();

        try {
            switch (opcion) {
                case 1:
                    System.out.println("--- EJERCICIO 1: LISTADO DE EMPLEADOS ---");
                    Empleado[] empleados = Ejercicio01.parsearEmpleados("empleados.xml");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                    }
                    break;

                case 2:
                    System.out.println("--- EJERCICIO 2: ESTADÍSTICAS POR DEPARTAMENTO ---");
                    Empleado[] empleadosArray = Ejercicio01.parsearEmpleados("empleados.xml");
                    List<Empleado> listaEmpleados = Arrays.asList(empleadosArray);

                    Map<String, List<Empleado>> porDepartamento = listaEmpleados.stream()
                            .collect(Collectors.groupingBy(Empleado::getDepartamento));

                    System.out.printf("%-20s | %-15s | %-15s%n", "Departamento", "Nº empleados", "Salario medio");
                    System.out.println("------------------------------------------------------------------");

                    for (Map.Entry<String, List<Empleado>> entry : porDepartamento.entrySet()) {
                        String departamento = entry.getKey();
                        List<Empleado> listaDept = entry.getValue();
                        int numEmpleados = listaDept.size();
                        double salarioMedio = listaDept.stream()
                                .mapToDouble(Empleado::getSalario)
                                .average()
                                .orElse(0.0);

                        System.out.printf("%-20s | %-15d | %-15.2f%n", departamento, numEmpleados, salarioMedio);
                    }
                    break;

                case 3:
                    System.out.println("--- EJERCICIO 3: BIBLIOTECA ---");
                    // Aquí llamarías a la lógica desarrollada para el Ejercicio 3
                    System.out.println("Ejecutando consultas de biblioteca...");
                    // Ejercicio03.ejecutar("biblioteca.xml");
                    break;

                case 4:
                    System.out.println("--- EJERCICIO 4: PEDIDOS ---");
                    System.out.print("Introduce el ID del pedido a consultar: ");
                    String idBuscado = scanner.nextLine();

                    Pedido[] pedidos = Pedido.parsearPedidos("pedidos.xml");
                    boolean encontrado = false;

                    for (Pedido pedido : pedidos) {
                        if (pedido.getId().equalsIgnoreCase(idBuscado)) {
                            encontrado = true;
                            System.out.println("\nItems del pedido: " + pedido.getId());
                            for (ItemPedido item : pedido.getItems()) {
                                System.out.printf("  * %s | Cant: %d | Precio U: %.2f | Subtotal: %.2f%n",
                                        item.getNombre(), item.getCantidad(), item.getPrecioUnitario(), item.getSubtotal());
                            }
                            System.out.println("\nValidación de totales:");
                            System.out.printf("Total recalculado: %.2f%n", pedido.calcularTotalReal());
                            System.out.printf("Total en XML:      %.2f%n", pedido.getTotal());

                            if (pedido.validarTotal()) {
                                System.out.println("El total del pedido coincide correctamente.");
                            } else {
                                System.out.println("El total del pedido NO coincide.");
                            }
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontró ningún pedido con el ID: " + idBuscado);
                    }
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } catch (IOException e) {
            System.err.println("Error al leer el fichero XML: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.err.println("Error al interpretar o configurar el parser DOM: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error al construir el DOM jerárquico del XML: " + e.getMessage());
        }
    }
}