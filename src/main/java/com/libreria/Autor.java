package com.libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Autor {
    private String nombre;
    private String nacionalidad;
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros != null ? libros : new ArrayList<>();
    }

    public void addLibro(Libro libro) {
        if (libro == null) return;
        if (!libros.contains(libro)) {
            libros.add(libro);
        }
        if (!libro.hasAutor(this)) {
            libro.addAutor(this);
        }
    }

    public void removeLibro(Libro libro) {
        if (libro == null) return;
        if (libros.remove(libro)) {
            if (libro.hasAutor(this)) {
                libro.removeAutor(this);
            }
        }
    }

    @Override
    public String toString() {
        String titulos = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", libros=[" + titulos + "]" +
                '}';
    }
}
