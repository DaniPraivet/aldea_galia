/**
 * Paquete para las vistas
 */
package programa.vistas;

import programa.clases.Aldea;
import programa.clases.Aldeano;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Lanzador de las clases Aldea y Aldeano
 * @see Aldea
 * @see Aldeano
 */
public class Main {
    /**
     * Instanciamos el objeto aldea
      */
    static Aldea galia = new Aldea(new TreeMap<>());
    /**
     * Es quien dictamina si el programa acaba o no, para facilitar el entendimiento del código
      */
    static boolean ejecucion;
    /**
     * Scanner que usaremos a lo largo del programa para que el usuario pueda interaccionar con el programa
     */
    static Scanner sc = new Scanner (System.in);

    /**
     * Método principal
     */
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

    /**
     * Aquí es donde se manejan las opciones que elige el usuario
     * @param opcion opción que envía el usuario mediante input de teclado
     * @return un valor booleano para determinar si se sigue ejecutando o no el programa
     */
    static boolean gestionarOpcion(String opcion) {
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

    /**
     * Menú del programa
     */
    static void mostrarMenu() {
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
