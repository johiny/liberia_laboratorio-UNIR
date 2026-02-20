package com.libreria.models;

public class Novela extends Libro {
    private String genero;

    public Novela() {
        super();
    }

    public Novela(String titulo, Autor[] autores, String isbn, int anioPublicacion, String genero) {
        super(titulo, autores, isbn, anioPublicacion);
        this.genero = genero;
    }

    public Novela(String titulo, Autor[] autores, String isbn, int anioPublicacion, double precio, String genero) {
        super(titulo, autores, isbn, anioPublicacion, precio);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Novela{" +
                "titulo='" + getTitulo() + '\'' +
                ", autores=" + (getAutores() != null ? java.util.Arrays.toString(getAutores()) : "[]") +
                ", isbn='" + getIsbn() + '\'' +
                ", anioPublicacion=" + getAnioPublicacion() +
                ", precio=" + getPrecio() +
                ", genero='" + genero + '\'' +
                '}';
    }
}
