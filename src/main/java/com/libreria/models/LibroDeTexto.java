package com.libreria.models;

public class LibroDeTexto extends Libro {
    private String nivelEducativo;

    public LibroDeTexto() {
        super();
    }

    public LibroDeTexto(String titulo, Autor[] autores, String isbn, int anioPublicacion, double precio, String nivelEducativo) {
        super(titulo, autores, isbn, anioPublicacion, precio);
        this.nivelEducativo = nivelEducativo;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    @Override
    public String toString() {
        return "LibroDeTexto{" +
                "titulo='" + getTitulo() + '\'' +
                ", autores=" + (getAutores() != null ? java.util.Arrays.toString(getAutores()) : "[]") +
                ", isbn='" + getIsbn() + '\'' +
                ", anioPublicacion=" + getAnioPublicacion() +
                ", precio=" + getPrecio() +
                ", nivelEducativo='" + nivelEducativo + '\'' +
                '}';
    }
}
