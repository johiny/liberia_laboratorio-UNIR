# Librería Paco — Administrador de Librería

Proyecto Java para la gestión de una librería desde la terminal.  
Permite agregar libros al catálogo, buscar por título o autor, vender libros a clientes y consultar el historial de compras.

---

## Requisitos

- Java JDK 17 o superior
- Maven 3.x *(opcional, ver alternativa sin Maven)*

Verificar versiones:

```bash
java -version
mvn -version
```

---

## Clonar el repositorio

```bash
git clone https://github.com/johiny/liberia_laboratorio-UNIR.git
cd liberia_laboratorio-UNIR
```

---

## Opción A — Con Maven

> ⚠️ Ejecutar **desde la raíz del proyecto** (donde está el `pom.xml`), no desde dentro de `src/`.

```bash
# Asegúrate de estar en la raíz del proyecto
cd liberia_laboratorio-UNIR

# Compilar y empaquetar
mvn package

# Ejecutar el JAR generado
java -jar target/libreria-paco-1.0-SNAPSHOT.jar
```

> El JAR ya tiene configurada `com.libreria.Libreria` como clase principal, por lo que no hace falta especificarla.

---

## Opción B — Sin Maven (solo javac)

```bash
# Compilar
find src -name "*.java" | xargs javac -d out

# Ejecutar
java -cp out com.libreria.Libreria
```

---

## Funcionalidades del menú

| Opción | Acción |
|--------|--------|
| `1` | Agregar un nuevo libro al catálogo |
| `2` | Buscar libro por título |
| `3` | Buscar libro por autor |
| `4` | Ver catálogo completo |
| `5` | Vender un libro a un cliente |
| `6` | Ver historial de compras de un cliente |
| `7` | Registrar nuevo cliente |
| `0` | Salir |

Al iniciar, se cargan automáticamente **3 libros de ejemplo y 3 clientes** para poder probar de inmediato.

---

## Estructura del proyecto

```
src/
└── main/java/com/libreria/
    ├── Libreria.java          # Punto de entrada y menú interactivo
    ├── database/
    │   ├── Catalogo.java      # Gestión del catálogo (en memoria)
    │   └── ConexionSQL.java   # Clase de conexión (reservada para BD)
    ├── models/
    │   ├── Libro.java
    │   ├── Autor.java
    │   ├── Cliente.java
    │   ├── Persona.java
    │   ├── Novela.java
    │   └── LibroDeTexto.java
    └── service/
        └── ServicioVenta.java # Lógica de venta
```
