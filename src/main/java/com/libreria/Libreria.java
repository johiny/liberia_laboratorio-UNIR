package com.libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Libreria {
    private List<Libro> catalogoLibros = new ArrayList<>();
    private String nombre;

    public Libreria() {
    }

    public Libreria(String nombre, List<Libro> catalogoLibros) {
        this.nombre = nombre;
        setCatalogoLibros(catalogoLibros);
    }

    public List<Libro> getCatalogoLibros() {
        return catalogoLibros;
    }
    
    public void setCatalogoLibros(List<Libro> catalogoLibros) {
        this.catalogoLibros = catalogoLibros != null ? new ArrayList<>(catalogoLibros) : new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean addLibro(Libro libro) {
        if (libro == null) return false;
        if (!catalogoLibros.contains(libro)) {
            catalogoLibros.add(libro);
            return true;
        }
        return false;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        if (titulo == null) return new ArrayList<>();
        String q = titulo.toLowerCase();
        return catalogoLibros.stream()
                .filter(l -> l.getTitulo() != null && l.getTitulo().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        if (nombreAutor == null) return new ArrayList<>();
        String q = nombreAutor.toLowerCase();
        return catalogoLibros.stream()
                .filter(l -> java.util.Arrays.stream(l.getAutores())
                        .anyMatch(a -> a.getNombre() != null && a.getNombre().toLowerCase().contains(q)))
                .collect(Collectors.toList());
    }

    public boolean estaDisponible(Libro libro) {
        if (libro == null) return false;
        return catalogoLibros.contains(libro);
    }

    public boolean venderLibro(Libro libro, Cliente cliente) {
        if (libro == null || cliente == null) return false;
        int idx = -1;
        for (int i = 0; i < catalogoLibros.size(); i++) {
            if (Objects.equals(catalogoLibros.get(i), libro)) { idx = i; break; }
        }
        if (idx == -1) return false; // no está en catálogo
        Libro sold = catalogoLibros.remove(idx);
        cliente.addLibroComprado(sold);
        return true;
    }

}
