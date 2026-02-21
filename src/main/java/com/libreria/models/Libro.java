package com.libreria.models;

import java.util.Arrays;
import java.util.Objects;

public class Libro {
    private String titulo;
    private Autor[] autores;
    private String isbn;
    private int anioPublicacion;
    private double precio;

    public Libro() {
    }

    public Libro(String titulo, Autor[] autores, String isbn, int anioPublicacion) {
        this.titulo = titulo;
        this.autores = autores;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
    }

    public Libro(String titulo, Autor[] autores, String isbn, int anioPublicacion, double precio) {
        this(titulo, autores, isbn, anioPublicacion);
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor[] getAutores() {
        return autores;
    }

    public void setAutores(Autor[] autores) {
        this.autores = autores;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean hasAutor(Autor autor) {
        if (autor == null || autores == null) return false;
        for (Autor a : autores) {
            if (a == autor) return true;
        }
        return false;
    }

    public void addAutor(Autor autor) {
        if (autor == null) return;
        if (hasAutor(autor)) return;
        if (autores == null) {
            autores = new Autor[]{autor};
        } else {
            Autor[] n = Arrays.copyOf(autores, autores.length + 1);
            n[autores.length] = autor;
            autores = n;
        }
        if (!autor.getLibros().contains(this)) {
            autor.addLibro(this);
        }
    }

    public void removeAutor(Autor autor) {
        if (autor == null || autores == null) return;
        int idx = -1;
        for (int i = 0; i < autores.length; i++) {
            if (autores[i] == autor) { idx = i; break; }
        }
        if (idx == -1) return;
        if (autores.length == 1) {
            autores = null;
        } else {
            Autor[] n = new Autor[autores.length - 1];
            System.arraycopy(autores, 0, n, 0, idx);
            System.arraycopy(autores, idx + 1, n, idx, autores.length - idx - 1);
            autores = n;
        }
        autor.removeLibro(this);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + (autores != null ? Arrays.toString(autores) : "[]") +
                ", isbn='" + isbn + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", precio=" + precio +
                '}';
    }
}
