package com.example.bibliotecaApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Data // Genera automáticamente los getters, setters, métodos `equals()`, `hashCode()`, `toString()`, etc., mediante Lombok.
@NoArgsConstructor // Genera un constructor sin parámetros requerido por JPA.
@AllArgsConstructor // Genera un constructor con todos los campos de la clase como parámetros.
public class Libro {

    @Id // Define que este campo 'id' es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor de 'id' será generado automáticamente por la base de datos (auto-incremental).
    private Long id; // Identificador único para cada libro.

    private String titulo; // Campo que almacena el título del libro.
    private String categoria; // Campo que almacena la categoría o género del libro.
    private boolean disponible; // Indica si el libro está disponible (true) o no (false).

    @ManyToOne // Define una relación de muchos a uno. Varios libros pueden estar asociados a un solo autor.
    @JoinColumn(name = "autor_id") // Especifica la columna de unión (clave foránea) que relaciona el libro con un autor.
    private Autor autor; // Representa el autor del libro, relacionado a la entidad `Autor`.
}

