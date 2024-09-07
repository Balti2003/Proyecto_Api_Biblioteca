package com.example.bibliotecaApi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaApi.entities.Libro;
import com.example.bibliotecaApi.repository.LibroRepository;

@Service // Marca esta clase como un servicio, representando la capa de lógica de negocio en la aplicación.
public class LibroService {

    @Autowired // Inyecta automáticamente una instancia de LibroRepository para utilizar sus métodos en la lógica del servicio.
    private LibroRepository libroRepository;

    public List<Libro> findAll() {
        // Llama al método findAll() del repositorio para obtener todos los libros de la base de datos.
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Long id) {
        // Busca un libro por su ID y devuelve un Optional que puede contener o no un libro.
        return libroRepository.findById(id);
    }

    public Libro save(Libro libro) {
        // Guarda un nuevo libro o actualiza uno existente en la base de datos.
        return libroRepository.save(libro);
    }

    public void deleteById(Long id) {
        // Elimina un libro de la base de datos utilizando su ID.
        libroRepository.deleteById(id);
    }

    public List<Libro> findByAutorId(Long idAutor) {
        // Filtra los libros por el ID del autor.
        // Obtiene todos los libros, filtra aquellos cuyo autor tiene el ID proporcionado, y devuelve la lista.
        return findAll().stream()
                .filter(libro -> libro.getAutor().getId().equals(idAutor))
                .collect(Collectors.toList());
    }

    public List<String> findDistinctCategorias() {
        // Obtiene una lista de todas las categorías distintas de los libros.
        // Mapea los libros a sus categorías, elimina duplicados usando `distinct()`, y convierte los resultados a una lista.
        return libroRepository.findAll()
                .stream()
                .map(Libro::getCategoria)
                .distinct()
                .toList();
    }

    public List<Libro> findByCategoria(String categoria) {
        // Busca libros por categoría utilizando un método personalizado definido en LibroRepository.
        return libroRepository.findByCategoria(categoria);
    }
}

