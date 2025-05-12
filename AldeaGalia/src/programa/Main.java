package programa;

import programa.clases.Aldea;
import programa.clases.Aldeano;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static Aldea galia = new Aldea(new TreeMap<>());
    static boolean ejecucion;
    static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        ejecucion = true;
        String opcion;
        galia.cargarDatosDesdeArchivoBinario();

        while (ejecucion) {
            mostrarMenu();

            opcion = sc.nextLine();

            ejecucion = gestionarOpcion(opcion);
        }

        sc.close();
    }

    private static boolean gestionarOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
                System.out.println("Nombre del aldeano:");
                String nombre = sc.nextLine();

                System.out.println("Profesión del aldeano:");
                String profesion = sc.nextLine();

                System.out.println("Número de romanos asesinados:");
                int numeroAsesinatos = Integer.parseInt(sc.nextLine());

                galia.agregarHabitante(new Aldeano(nombre,profesion,numeroAsesinatos));
            }
            case "2" -> {
                System.out.println("Dime el nombre del aldeano que quieres echar: ");
                String nombreAldeanoAEchar = sc.nextLine();
                galia.eliminarHabitante(nombreAldeanoAEchar);
            }
            case "3" -> {
                System.out.println("""
                        0 -> se ordena por clave.
                        1 -> se ordena por nombre.
                        2 -> se ordena por profesion.
                        3 -> se ordena por romanos derrotados.
                        
                        Ingresa tu opción
                        """);
                int metodoOrdenacion = Integer.parseInt(sc.nextLine());
                galia.ordenarLista(metodoOrdenacion);
            }
            case "4" -> {
                System.out.println("Dime el nombre del aldeano que quieres buscar: ");
                String nombreAldeanoABuscar = sc.nextLine();
                galia.buscarHabitantePorNombre(nombreAldeanoABuscar);
            }
            case "5" -> {
                System.out.println("Dime la profesión en la que trabajaba ese aldeano: ");
                String nombreProfesion = sc.nextLine();
                galia.buscarHabitantePorProfesion(nombreProfesion);
            }
            case "6" -> {
                System.out.println("Población: " + galia.getRegistro().size() + " personas.\nIngresa la cantidad de aldeanos que quieres echar de manera aleatoria");
                int numeroDeAldeanosAExpulsar = Integer.parseInt(sc.nextLine());
                galia.expulsarAGenteAleatoriamenteDeLaAldea(numeroDeAldeanosAExpulsar);
            }
            case "7" -> galia.mostrarListadoDeHabitantes();
            case "0" -> {
                galia.guardarDatosEnArchivoBinario();
                ejecucion = false;
            }
            default -> System.out.println("Opción inválida, intentalo de nuevo.");
        }
        return ejecucion;
    }

    private static void mostrarMenu() {
        System.out.println("-".repeat(24) + " Menú " + "-".repeat(24));
        System.out.println("\t1. Agregar habitante.");
        System.out.println("\t2. Eliminar habitante.");
        System.out.println("\t3. Ordenar lista.");
        System.out.println("\t4. Buscar habitante por nombre.");
        System.out.println("\t5. Buscar habitante por profesión.");
        System.out.println("\t6. Expulsar a habitantes de manera aleatoria.");
        System.out.println("\t7. Mostrar listado de habitantes.");
        System.out.println("\t0. Salir.");
    }
}
