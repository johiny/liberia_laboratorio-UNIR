package com.libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cliente {
    private String nombre;
    private String id;
    private List<Libro> librosComprados = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Cliente(String nombre, String id, List<Libro> librosComprados) {
        this.nombre = nombre;
        this.id = id;
        this.librosComprados = librosComprados != null ? new ArrayList<>(librosComprados) : new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", librosComprados=[" + titulos + "]" +
                '}';
    }
}
