package com.libreria.database;

import com.libreria.models.Autor;
import com.libreria.models.Libro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.libreria.models.Cliente;

public class Catalogo {

    private List<Libro> libros = new ArrayList<>();
    // Registro de ventas: libro -> cliente que lo compró
    private Map<Libro, Cliente> vendidos = new LinkedHashMap<>();

    // ── Agregar un libro al catálogo ──────────────────────────────────────────
    public boolean addLibro(Libro libro) {
        if (libro == null) return false;
        if (estaDisponible(libro)) {
            System.out.println("El libro ya está en el catálogo.");
            return false;
        }
        libros.add(libro);
        return true;
    }

    // ── Verificar si un libro está disponible ─────────────────────────────────
    public boolean estaDisponible(Libro libro) {
        if (libro == null) return false;
        return libros.contains(libro);
    }

    // ── Retirar un libro del catálogo al venderlo y registrar comprador ─────────
    public boolean retirarLibro(Libro libro, Cliente comprador) {
        if (libro == null) return false;
        if (libros.remove(libro)) {
            vendidos.put(libro, comprador);
            return true;
        }
        return false;
    }

    // ── Libros vendidos con su comprador ──────────────────────────────────────
    public Map<Libro, Cliente> getVendidos() {
        return new LinkedHashMap<>(vendidos);
    }

    // ── Buscar por título (parcial, sin distinción de mayúsculas) ─────────────
    public List<Libro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) return new ArrayList<>(libros);
        String f = titulo.toLowerCase();
        return libros.stream()
                .filter(l -> l.getTitulo() != null && l.getTitulo().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    // ── Buscar por autor (parcial, sin distinción de mayúsculas) ──────────────
    public List<Libro> buscarPorAutor(String nombreAutor) {
        if (nombreAutor == null || nombreAutor.isBlank()) return new ArrayList<>(libros);
        String f = nombreAutor.toLowerCase();
        return libros.stream()
                .filter(l -> {
                    if (l.getAutores() == null) return false;
                    for (Autor a : l.getAutores())
                        if (a.getNombre() != null && a.getNombre().toLowerCase().contains(f)) return true;
                    return false;
                })
                .collect(Collectors.toList());
    }

    // ── Devolver todos los libros del catálogo ────────────────────────────────
    public List<Libro> getLibros() {
        return new ArrayList<>(libros);
    }
}
