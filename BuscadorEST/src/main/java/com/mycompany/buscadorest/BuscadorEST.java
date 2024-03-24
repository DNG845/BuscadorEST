/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
to change this license
 */

package com.mycompany.buscadorest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author danny
 */
public class BuscadorEST {

    public static void main(String[] args) {
        String archivo = "C:\\Users\\danny\\OneDrive\\Documentos\\"
                + "NetBeansProjects\\BuscadorEST\\lista.txt";/*
        El formato del archito txt esta en Nombre, carnet*/

        try {
            LinkedList<String> estudiantes = ListaArchivo(archivo);

            if (estudiantes == null) {
                System.out.println("No se pudo cargar la lista desde "
                        + "el archivo.");
                return;
            }
            
            MostrarLista(estudiantes);

            Scanner scanner = new Scanner(System.in);
            String opcion;
            do {
                System.out.println("\n¿Que accion desea realizar? (agregar, "
                        + "buscar, editar, eliminar, salir)");
                opcion = scanner.next();

                switch (opcion.toLowerCase()) {
                    case "agregar":
                        agregarEstudiante(scanner, estudiantes);
                        break;
                    case "buscar":
                        buscarEstudiante(scanner, estudiantes);
                        break;
                    case "editar":
                        editarEstudiante(scanner, estudiantes);
                        break;
                    case "eliminar":
                        eliminarEstudiante(scanner, estudiantes);
                        break;
                    case "salir":
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            } while (!opcion.equalsIgnoreCase("salir"));

            System.out.println("\nLista actualizada de estudiantes:");
            imprimirLista(estudiantes);
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static LinkedList<String> ListaArchivo(String archivo) 
            throws IOException {
        LinkedList<String> estudiantes = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                estudiantes.add(linea);
            }
        }

        return estudiantes;
    }

    public static void MostrarLista(LinkedList<String> estudiantes) {
        Collections.sort(estudiantes);
        System.out.println("Lista de estudiantes ordenada:");
        imprimirLista(estudiantes);
    }

    public static void imprimirLista(LinkedList<String> estudiantes) {
        for (String estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    public static void agregarEstudiante(Scanner scanner, 
            LinkedList<String> estudiantes) {
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.next();
        System.out.println("Ingrese el carnet del estudiante:");
        String carnet = scanner.next();
        System.out.println("¿Desea agregar el estudiante al inicio (1)"
                + " o al final (2) de la lista?");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            agregarAlInicio(nombre, carnet, estudiantes);
        } else if (opcion == 2) {
            agregarAlFinal(nombre, carnet, estudiantes);
        } else {
            System.out.println("Opción no válida. El estudiante será agregado "
                    + "al final por defecto.");
            agregarAlFinal(nombre, carnet, estudiantes);
        }
    }

    public static void buscarEstudiante(Scanner scanner, LinkedList<String> 
            estudiantes) {
        System.out.println("Ingrese el carnet del estudiante que "
                + "desea buscar:");
        String carnetBuscar = scanner.next();
        buscarEstudiante(carnetBuscar, estudiantes);
    }

    public static void editarEstudiante(Scanner scanner, LinkedList<String> 
            estudiantes) {
        System.out.println("Ingrese el carnet del estudiante que desea "
                + "editar:");
        String carnetEditar = scanner.next();
        editarEstudiante(carnetEditar, estudiantes);
    }

    public static void eliminarEstudiante(Scanner scanner, LinkedList<String> 
            estudiantes) {
        System.out.println("Ingrese el carnet del estudiante que desea "
                + "eliminar:");
        String carnetEliminar = scanner.next();
        eliminarEstudiante(carnetEliminar, estudiantes);
    }

    public static void agregarAlInicio(String nombre, String carnet, 
            LinkedList<String> estudiantes) {
        estudiantes.addFirst(nombre + ", " + carnet);
    }

    public static void agregarAlFinal(String nombre, String carnet, 
            LinkedList<String> estudiantes) {
        estudiantes.addLast(nombre + ", " + carnet);
    }

    public static void buscarEstudiante(String carnet, 
            LinkedList<String> estudiantes) {
        boolean encontrado = false;
        for (String estudiante : estudiantes) {
            if (estudiante.contains(carnet)) {
                System.out.println("Estudiante encontrado: " + estudiante);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante con carnet " + carnet + " no "
                    + "encontrado.");
        }
    }

    public static void editarEstudiante(String carnet, 
            LinkedList<String> estudiantes) {
        boolean encontrado = false;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < estudiantes.size(); i++) {
            String estudiante = estudiantes.get(i);
            if (estudiante.contains(carnet)) {
                System.out.println("Estudiante encontrado: " + estudiante);
                System.out.println("Ingrese el nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                estudiantes.set(i, nuevoNombre + ", " + carnet);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante con carnet " + carnet + " no "
                    + "encontrado.");
        } else {
            System.out.println("Estudiante actualizado correctamente.");
        }
    }

    public static void eliminarEstudiante(String carnet, 
            LinkedList<String> estudiantes) {
        boolean eliminado = false;
        for (String estudiante : estudiantes) {
            if (estudiante.contains(carnet)) {
                estudiantes.remove(estudiante);
                System.out.println("Estudiante con carnet " + carnet + " "
                        + "eliminado correctamente.");
                eliminado = true;
                break;
            }
        }
        if (!eliminado) {
            System.out.println("Estudiante con carnet " + carnet + " no "
                    + "encontrado.");
        }
    }
}
