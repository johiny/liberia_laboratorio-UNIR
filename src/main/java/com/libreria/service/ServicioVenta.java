package com.libreria.service;

import com.libreria.database.Catalogo;
import com.libreria.models.Cliente;
import com.libreria.models.Libro;

public class ServicioVenta {
    // El tipo exacto es "Catalogo", el nombre de la variable es "catalogo"
    private Catalogo catalogo; 

    // Inyección de dependencias pura
    public ServicioVenta(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    // Constructor por defecto: crea un catálogo por defecto
    public ServicioVenta() {
        this.catalogo = new Catalogo();
    }

    public boolean venderLibro(Libro libro, Cliente cliente) {
        if (libro == null || cliente == null) return false;

        // El servicio habla con el catálogo
        if (catalogo.estaDisponible(libro)) {
            catalogo.retirarLibro(libro, cliente);
            cliente.addLibroComprado(libro);
            return true;
        }
        return false;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }
}