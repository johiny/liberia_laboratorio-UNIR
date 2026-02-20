package com.libreria;

import com.libreria.database.Catalogo;
import com.libreria.models.Autor;
import com.libreria.models.Cliente;
import com.libreria.models.Libro;
import com.libreria.service.ServicioVenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Libreria {

    private static final Catalogo      catalogo      = new Catalogo();
    private static final ServicioVenta servicioVenta = new ServicioVenta(catalogo);
    private static final List<Cliente> clientes      = new ArrayList<>();
    private static final Scanner       scanner       = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   Bienvenido a Librería Paco!    ║");
        System.out.println("╚══════════════════════════════════╝");
        cargarDatosDemo();
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Elige una opción: ");
            System.out.println();
            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAutor();
                case 4 -> verCatalogo();
                case 5 -> venderLibro();
                case 6 -> verHistorialCliente();
                case 7 -> registrarCliente();
                case 8 -> verLibrosVendidos();
                case 0 -> continuar = false;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("\n¡Hasta pronto!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n──────────── MENÚ ────────────────");
        System.out.println(" 1. Agregar libro al catálogo");
        System.out.println(" 2. Buscar libro por título");
        System.out.println(" 3. Buscar libro por autor");
        System.out.println(" 4. Ver catálogo completo");
        System.out.println(" 5. Vender libro a un cliente");
        System.out.println(" 6. Ver historial de un cliente");
        System.out.println(" 7. Registrar nuevo cliente");
        System.out.println(" 8. Ver libros vendidos y compradores");
        System.out.println(" 0. Salir");
        System.out.println("──────────────────────────────────");
    }

    private static void agregarLibro() {
        System.out.println("=== Agregar libro ===");
        String titulo       = leerTexto("Título: ");
        String isbn         = leerTexto("ISBN: ");
        int    anio         = leerEntero("Año de publicación: ");
        double precio       = leerDouble("Precio: ");
        String nombreAutor  = leerTexto("Nombre del autor: ");
        String nacionalidad = leerTexto("Nacionalidad del autor: ");
        Autor autor = new Autor(nombreAutor, nacionalidad);
        Libro libro = new Libro(titulo, new Autor[]{autor}, isbn, anio, precio);
        if (catalogo.addLibro(libro)) {
            System.out.println("✔ Libro \"" + titulo + "\" agregado al catálogo.");
        } else {
            System.out.println("✘ El libro ya existe en el catálogo.");
        }
    }

    private static void buscarPorTitulo() {
        System.out.println("=== Buscar por título ===");
        mostrarResultados(catalogo.buscarPorTitulo(leerTexto("Título (o parte): ")));
    }

    private static void buscarPorAutor() {
        System.out.println("=== Buscar por autor ===");
        mostrarResultados(catalogo.buscarPorAutor(leerTexto("Autor (o parte): ")));
    }

    private static void verCatalogo() {
        System.out.println("=== Catálogo disponible ===");
        List<Libro> todos = catalogo.getLibros();
        if (todos.isEmpty()) System.out.println("El catálogo está vacío.");
        else imprimirLibros(todos);
    }

    private static void venderLibro() {
        System.out.println("=== Vender libro ===");
        if (catalogo.getLibros().isEmpty()) { System.out.println("No hay libros disponibles."); return; }
        if (clientes.isEmpty()) { System.out.println("No hay clientes. Usa la opción 7 primero."); return; }

        List<Libro> disponibles = catalogo.getLibros();
        imprimirLibrosNumerados(disponibles);
        int idxLibro = leerEntero("Número de libro (0 cancela): ");
        if (idxLibro == 0) return;
        if (idxLibro < 1 || idxLibro > disponibles.size()) { System.out.println("Número inválido."); return; }
        Libro libroElegido = disponibles.get(idxLibro - 1);

        System.out.println("\nClientes:");
        for (int i = 0; i < clientes.size(); i++)
            System.out.printf("  %d. %s (ID: %s)%n", i+1, clientes.get(i).getNombre(), clientes.get(i).getId());
        int idxCliente = leerEntero("Número de cliente (0 cancela): ");
        if (idxCliente == 0) return;
        if (idxCliente < 1 || idxCliente > clientes.size()) { System.out.println("Número inválido."); return; }
        Cliente clienteElegido = clientes.get(idxCliente - 1);

        if (servicioVenta.venderLibro(libroElegido, clienteElegido)) {
            System.out.printf("✔ \"%s\" vendido a %s por $%.2f%n",
                libroElegido.getTitulo(), clienteElegido.getNombre(), libroElegido.getPrecio());
        } else {
            System.out.println("✘ No se pudo completar la venta.");
        }
    }

    private static void verHistorialCliente() {
        System.out.println("=== Historial de compras ===");
        if (clientes.isEmpty()) { System.out.println("No hay clientes registrados."); return; }
        for (int i = 0; i < clientes.size(); i++)
            System.out.printf("  %d. %s (ID: %s)%n", i+1, clientes.get(i).getNombre(), clientes.get(i).getId());
        int idx = leerEntero("Número de cliente: ");
        if (idx < 1 || idx > clientes.size()) { System.out.println("Número inválido."); return; }
        Cliente c = clientes.get(idx - 1);
        List<Libro> comprados = c.getLibrosComprados();
        System.out.println("\nLibros comprados por " + c.getNombre() + ":");
        if (comprados.isEmpty()) System.out.println("  (ninguno)");
        else imprimirLibros(comprados);
    }

    private static void registrarCliente() {
        System.out.println("=== Registrar cliente ===");
        clientes.add(new Cliente(leerTexto("Nombre: "), leerTexto("ID / DNI: "), null));
        System.out.println("✔ Cliente registrado.");
    }

    private static void verLibrosVendidos() {
        System.out.println("=== Libros vendidos ===");
        Map<Libro, Cliente> vendidos = catalogo.getVendidos();
        if (vendidos.isEmpty()) {
            System.out.println("  (ningún libro vendido todavía)");
            return;
        }
        System.out.printf("  %-35s | %-25s | %s%n", "Título", "Comprador", "ID");
        System.out.println("  " + "-".repeat(72));
        for (Map.Entry<Libro, Cliente> e : vendidos.entrySet()) {
            System.out.printf("  %-35s | %-25s | %s%n",
                    e.getKey().getTitulo(),
                    e.getValue().getNombre(),
                    e.getValue().getId());
        }
    }

    private static void cargarDatosDemo() {
        Autor garcia    = new Autor("Gabriel García Márquez", "Colombiana");
        Autor orwell    = new Autor("George Orwell", "Británica");
        Autor cervantes = new Autor("Miguel de Cervantes", "Española");
        Autor Nietzsche   = new Autor("Friedrich Nietzsche", "Alemana");
        Autor poe       = new Autor("Edgar Allan Poe", "Estadounidense");
        catalogo.addLibro(new Libro("Cien años de soledad",    new Autor[]{garcia},    "978-0-06-088328-7", 1967, 18.99));
        catalogo.addLibro(new Libro("1984",                    new Autor[]{orwell},    "978-0-45-228423-4", 1949, 14.50));
        catalogo.addLibro(new Libro("Don Quijote de la Mancha",new Autor[]{cervantes}, "978-84-376-0494-7", 1605, 22.00));
        catalogo.addLibro(new Libro("Así habló Zaratustra",     new Autor[]{Nietzsche}, "978-84-206-3512-0", 1883, 25.00));
        catalogo.addLibro(new Libro("El cuervo",                 new Autor[]{poe},       "978-0-45-228423-5", 1845, 12.00));
        clientes.add(new Cliente("Ana Pérez",   "12345678A", null));
        clientes.add(new Cliente("Carlos Ruiz", "87654321B", null));
        clientes.add(new Cliente("Lucía Gómez", "11223344C", null));
        clientes.add(new Cliente("Miguel Torres", "44332211D", null));
        System.out.println("(5 libros y 4 clientes cargados al inicio)\n");
    }

    private static void mostrarResultados(List<Libro> r) {
        if (r.isEmpty()) System.out.println("No se encontraron resultados.");
        else { System.out.println(r.size() + " resultado(s):"); imprimirLibros(r); }
    }

    private static void imprimirLibros(List<Libro> lista) {
        for (Libro l : lista)
            System.out.printf("  • %-35s | %-25s | %s | %d | $%.2f%n",
                l.getTitulo(), autoresStr(l), l.getIsbn(), l.getAnioPublicacion(), l.getPrecio());
    }

    private static void imprimirLibrosNumerados(List<Libro> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Libro l = lista.get(i);
            System.out.printf("  %d. %-35s | %-25s | $%.2f%n", i+1, l.getTitulo(), autoresStr(l), l.getPrecio());
        }
    }

    private static String autoresStr(Libro l) {
        if (l.getAutores() == null || l.getAutores().length == 0) return "Desconocido";
        StringBuilder sb = new StringBuilder();
        for (Autor a : l.getAutores()) { if (sb.length() > 0) sb.append(", "); sb.append(a.getNombre()); }
        return sb.toString();
    }

    private static String leerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  ✘ Ingresa un número entero válido."); }
        }
    }

    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  ✘ Ingresa un número decimal válido (usa punto)."); }
        }
    }
}
