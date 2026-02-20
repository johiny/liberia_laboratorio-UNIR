package com.libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente extends Persona {
    private String id;
    private List<Libro> librosComprados = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String id, List<Libro> librosComprados) {
        super(nombre);
        this.id = id;
        this.librosComprados = librosComprados != null ? new ArrayList<>(librosComprados) : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Libro> getLibrosComprados() {
        return librosComprados;
    }

    public void setLibrosComprados(List<Libro> librosComprados) {
        this.librosComprados = librosComprados != null ? new ArrayList<>(librosComprados) : new ArrayList<>();
    }

    public void addLibroComprado(Libro libro) {
        if (libro == null) return;
        if (!librosComprados.contains(libro)) {
            librosComprados.add(libro);
        }
    }

    public void removeLibroComprado(Libro libro) {
        if (libro == null) return;
        librosComprados.remove(libro);
    }

    @Override
    public String toString() {
        String titulos = librosComprados.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return "Cliente{" +
            "nombre='" + getNombre() + '\'' +
            ", id='" + id + '\'' +
            ", librosComprados=[" + titulos + "]" +
            '}';
    }
}
