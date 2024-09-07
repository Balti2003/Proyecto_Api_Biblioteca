package com.example.bibliotecaApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bibliotecaApi.entities.Autor;

@Repository // Anotación que indica que esta interfaz es un componente de acceso a datos (DAO)
//responsable de la interacción con la base de datos.
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Extiende JpaRepository, lo que proporciona métodos CRUD (crear, leer, actualizar, eliminar) y 
    //consultas personalizadas para la entidad Autor. La entidad es Autor y el tipo de su clave primaria (id) es Long.
}
