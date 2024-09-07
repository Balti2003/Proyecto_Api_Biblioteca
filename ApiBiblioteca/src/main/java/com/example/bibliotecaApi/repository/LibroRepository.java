package com.example.bibliotecaApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bibliotecaApi.entities.Libro;

@Repository // Marca esta interfaz como un componente de acceso a datos (DAO), responsable de la interacción con la base de datos.
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Extiende JpaRepository, lo que proporciona métodos CRUD (crear, leer, actualizar, eliminar) para la entidad Libro.
    // La entidad es Libro y el tipo de su clave primaria (id) es Long.

    @Query("SELECT l FROM Libro l WHERE l.categoria = :categoria") 
    // Define una consulta personalizada usando JPQL (Java Persistence Query Language).
    // Se seleccionan todos los libros (l) de la entidad Libro cuya categoría coincide con el parámetro :categoria.

    List<Libro> findByCategoria(String categoria); 
    // Método personalizado para buscar libros según su categoría. 
    // El parámetro `categoria` será mapeado al valor en la consulta JPQL.
}

