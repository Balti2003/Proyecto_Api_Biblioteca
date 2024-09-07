package com.example.bibliotecaApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Data // Genera automáticamente los getters, setters, métodos `equals()`, `hashCode()`, `toString()` y otros métodos útiles de Lombok.
@NoArgsConstructor // Genera un constructor sin parámetros automáticamente, requerido por JPA.
@AllArgsConstructor // Genera un constructor con todos los campos como parámetros, útil para la creación rápida de objetos.
public class Autor {

    @Id // Define que este campo 'id' es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor del campo 'id' será generado automáticamente por la base de datos (auto-incremental).
    private Long id; // Identificador único para cada autor.

    private String nombre; // Campo que almacena el nombre del autor.
    private String pais; // Campo que almacena el país de origen del autor.
}

