/**
 * Paquete para las clases
 */
package programa.clases;

import java.io.*;
import java.util.*;

/**
 * Es un lugar donde residen uno o muchos aldeanos
 * @see Aldeano
 * @see programa.vistas.Main
 */
public class Aldea {
    /**
     * Aquí es donde se almacenarán los aldeanos y sus identificadores numéricos
     */
    Map<Integer, Aldeano> registro;
    /**
     * Archivo donde se almacenará el registro de la aldea
     */
    static final File ARCHIVO_BINARIO = new File("src/programa/datos/registro.dat");

    /**
     * Constructor de la clase aldea
     * @param registro mapa que almacene enteros por clave y aldeanos por valor
     */
    public Aldea(Map<Integer, Aldeano> registro) {
        this.registro = registro;
    }

    /**
     * Recibir el registro de la aldea
     * @return registro de la aldea
     */
    public Map<Integer, Aldeano> getRegistro() {
        return registro;
    }



    /**
     * Agregar un habitante en el registro
     * @param a aldeano para añadirlo al mapa
     */
    public void agregarHabitante(Aldeano a) {
        registro.put(registro.size()+1,a);
    }

    /**
     * Eliminar habitante
     * @param nombre nombre del habitante a buscar para obtener su id
     */
    public void eliminarHabitante(String nombre) {
        System.out.println("Intentando eliminar al aldeano con nombre: " + nombre);
        Aldeano aldeanoAEliminar = buscarHabitantePorNombre(nombre);

        if (aldeanoAEliminar == null) {
            System.out.println("No se encontró ningún aldeano con ese nombre.");
            return;
        }

        boolean encontrado = false;
        Integer idAldeanoAEliminar = null;

        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            System.out.println("Revisando ID " + a.getKey() + " con aldeano: " + a.getValue().getNombre());
            if (a.getValue().equals(aldeanoAEliminar)) {
                encontrado = true;
                idAldeanoAEliminar = a.getKey();
                System.out.println("Encontrado. ID a eliminar: " + idAldeanoAEliminar);
            }
        }

        if (encontrado && idAldeanoAEliminar != null) {
            registro.remove(idAldeanoAEliminar);
            System.out.println("Programa.Clases.Aldeano eliminado con ID: " + idAldeanoAEliminar);
        } else {
            System.out.println("El aldeano no estaba registrado.");
        }
    }


    /**
     * Si es 0, se ordena por clave.
     * Si es 1, se ordena por nombre.
     * Si es 2, se ordena por profesion.
     * Si es 3, se ordena por romanos derrotados.
     * @param metodoDeOrdenacion en función del valor recibido como parámetro, se debe ordenar la
     * vista del mapa
     */
    public void ordenarLista(int metodoDeOrdenacion) {
        Comparator <Map.Entry<Integer, Aldeano>> comparador;

        switch (metodoDeOrdenacion) {
            case 0 -> comparador = Map.Entry.comparingByKey();
            case 1 -> comparador = Comparator.comparing(a -> a.getValue().getNombre(), String.CASE_INSENSITIVE_ORDER);
            case 2 -> comparador = Comparator.comparing(a -> a.getValue().getProfesion(), String.CASE_INSENSITIVE_ORDER);
            case 3 -> comparador = Comparator.comparingInt((Map.Entry<Integer, Aldeano> a) -> a.getValue().getNumeroRomanosAsesinados()).reversed();
            default -> {
                System.out.println("Criterio no válido");
                return;
            }
        }

        System.out.println("\n\nPoblación: " + registro.size() + " aldeanos.");
        System.out.println("=".repeat(65));
        System.out.println(String.format("| %-4s | %-20s | %-20s | %-6s |", "ID", "Nombre", "Profesión", "Bajas"));
        System.out.println("-".repeat(65));

        registro.entrySet().stream()
                .sorted(comparador)
                .forEach(a -> System.out.println(String.format("| %-4d | %s |", a.getKey(), a.getValue().toString())));

        System.out.println("=".repeat(65)+"\n");
    }

    /**
     * Busca por nombre y luego te muestra una lista y te pregunta con cuál aldeano te quedas
     * @param nombre nombre del Aldeano a buscar
     * @return aldeano seleccionado
     */
    public Aldeano buscarHabitantePorNombre(String nombre) {
        Scanner sc = new Scanner(System.in);
        int registros = 0;
        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            if (a.getValue().getNombre().contains(nombre)) {
                registros++;
                System.out.println(a.getKey() + " - " + a.getValue());
            }
        }

        if (registros > 0) {
            System.out.println("\nDime la clave del aldeano que estabas buscando:");
            int claveAldeanoBuscado = sc.nextInt();
            return registro.get(claveAldeanoBuscado);
        } else {
            System.out.println("No se han encontrado aldeanos con ese nombre.");
            return null;
        }
    }

    /**
     * Busca por profesión y luego te muestra una lista y te pregunta con cuál aldeano te quedas
     * @param profesion nombre del trabajo que realiza el Aldeano
     * @return aldeano seleccionado
     */
    public Aldeano buscarHabitantePorProfesion(String profesion) {
        Scanner sc = new Scanner(System.in);
        int registros = 0;
        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            if (a.getValue().getProfesion().contains(profesion)) {
                registros++;
                System.out.println(a.getKey() + " - " + a.getValue());
            }
        }

        if (registros > 0) {
            System.out.println("\nDime la clave del aldeano que estabas buscando:");
            int claveAldeanoBuscado = sc.nextInt();
            return registro.get(claveAldeanoBuscado);
        } else {
            System.out.println("No se han encontrado aldeanos con esa profesión.");
            return null;
        }
    }

    /**
     * Usa el toString() de este objeto y lo muestra de manera personalizada
     */
    public void mostrarListadoDeHabitantes() {
        System.out.println(this);
    }

    /**
     * Expulsa a una cantidad específica de Aldeanos de manera aleatoria
     * @param cantidad cantidad de Aldeanos a expulsar
     */
    public void expulsarAGenteAleatoriamenteDeLaAldea(int cantidad) {
        if (cantidad <= 0 || cantidad > registro.size()) {
            System.out.println("La cantidad introducida inválida.");
            return;
        }

        List<Integer> claves = new ArrayList<>(registro.keySet());
        Collections.shuffle(claves);

        for (int i = 0; i < cantidad; i++) {
            Integer clave = claves.get(i);
            System.out.println("Expulsando a: " + registro.get(clave));
            registro.remove(clave);
        }

        Map<Integer, Aldeano> nuevoRegistro = new TreeMap<>();
        int nuevaClave = 1;
        for (Aldeano a : registro.values()) {
            nuevoRegistro.put(nuevaClave++, a);
        }
        registro = nuevoRegistro;
    }

    /**
     * Guardamos los datos almacenados durante la ejecución de este programa en un fichero binario
     */
    public void guardarDatosEnArchivoBinario() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BINARIO))) {
            oos.writeObject(registro);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cargar los datos almacenados anteriormente en un archivo binario, si no existe o no tiene datos, no se cargan ningún dato
     */
    public void cargarDatosDesdeArchivoBinario() {
        if (!ARCHIVO_BINARIO.exists()) {
            System.out.println("El archivo de datos no existe.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BINARIO))) {
            registro = (Map<Integer, Aldeano>) ois.readObject();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    /**
     * Forma personalizada de mostrar el objeto Aldea
     * @return texto a mostrar
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("Población: ").append(registro.size()).append(" aldeanos.\n");
        texto.append("=".repeat(65)).append("\n");
        texto.append(String.format("| %-4s | %-20s | %-20s | %-6s |\n", "ID", "Nombre", "Profesión", "Bajas"));
        texto.append("-".repeat(65)).append("\n");

        for (Map.Entry<Integer, Aldeano> entry : registro.entrySet()) {
            Aldeano a = entry.getValue();
            texto.append(String.format("| %-4d | %s |\n", entry.getKey(), a.toString()));
        }

        texto.append("=".repeat(65)).append("\n");
        return texto.toString();
    }
}