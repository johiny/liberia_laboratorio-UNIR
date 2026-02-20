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

    public boolean venderLibro(Libro libro, Cliente cliente) {
        if (libro == null || cliente == null) return false;

        // El servicio habla con el catálogo, no con la "base de datos"
        if (catalogo.estaDisponible(libro)) {
            catalogo.retirarLibro(libro, cliente);
            cliente.addLibroComprado(libro);
            return true;
        }
        return false;
    }
}