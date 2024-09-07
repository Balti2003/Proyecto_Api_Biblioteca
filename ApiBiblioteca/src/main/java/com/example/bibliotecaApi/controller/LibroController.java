package com.example.bibliotecaApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotecaApi.entities.Libro;
import com.example.bibliotecaApi.service.LibroService;

@CrossOrigin("*") // Permite que cualquier origen (dominio) acceda a los recursos de este controlador mediante CORS.
@RequestMapping("/api/libros") // Mapea las solicitudes HTTP que comienzan con "/api/libros" a este controlador.
@RestController // Define que esta clase es un controlador REST, manejando solicitudes HTTP y devolviendo respuestas en formato JSON.
public class LibroController {

    @Autowired // Inyecta automáticamente una instancia de la clase LibroService para usar sus métodos en este controlador.
    private LibroService libroService;

    @GetMapping // Mapea las solicitudes HTTP GET a este método, que se ejecutará cuando alguien acceda a "/api/libros" vía GET.
    public List<Libro> getAllLibros() {
        return libroService.findAll(); // Llama al servicio LibroService para obtener la lista de todos los libros y la devuelve en formato JSON.
    }

    @GetMapping("/{id}") // Mapea las solicitudes HTTP GET que incluyen un parámetro 'id' a este método.
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) { 
        // Busca un libro por su ID. Si lo encuentra, devuelve un código de respuesta 200 (OK) con el libro.
        // Si no lo encuentra, devuelve un código 404 (Not Found).
        return libroService.findById(id)
                .map(ResponseEntity::ok) // Si el libro está presente, devuelve la respuesta con el libro (status 200).
                .orElse(ResponseEntity.notFound().build()); // Si no lo encuentra, devuelve una respuesta con status 404.
    }

    @PostMapping // Mapea las solicitudes HTTP POST a este método, que se ejecutará para crear un nuevo libro.
    public Libro crearLibro(@RequestBody Libro libro) { 
        // Crea un nuevo libro. El libro se pasa en el cuerpo de la solicitud en formato JSON.
        return libroService.save(libro); // Guarda el nuevo libro en la base de datos y lo devuelve.
    }

    @DeleteMapping("/{id}") // Mapea las solicitudes HTTP DELETE que incluyen un parámetro 'id' a este método.
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) { 
        // Elimina un libro por su ID.
        libroService.deleteById(id); // Llama al servicio para eliminar el libro por su ID.
        return ResponseEntity.noContent().build(); // Devuelve una respuesta vacía con un código 204 (No Content) si la eliminación fue exitosa.
    }

    @PutMapping("/{id}") // Mapea las solicitudes HTTP PUT que incluyen un parámetro 'id' a este método.
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro) { 
        // Actualiza un libro existente. El libro actualizado se pasa en el cuerpo de la solicitud en formato JSON.

        return libroService.findById(id)
                .map(existingLibro -> { 
                    // Si encuentra el libro, actualiza su información.
                    libro.setId(existingLibro.getId()); // Establece el mismo ID en el libro para asegurar que estamos actualizando el libro correcto.
                    return ResponseEntity.ok(libroService.save(libro)); // Guarda el libro actualizado y devuelve una respuesta 200 (OK) con el libro.
                })
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra el libro, devuelve una respuesta con código 404 (Not Found).
    }

    @GetMapping("/autor/{idAutor}") // Mapea las solicitudes HTTP GET que incluyen un parámetro 'idAutor' a este método.
    public List<Libro> getLibrosByAutor(@PathVariable Long idAutor) { 
        // Devuelve una lista de libros filtrados por el ID del autor.
        return libroService.findByAutorId(idAutor); // Llama al servicio para obtener todos los libros de un autor específico.
    }

    @GetMapping("/categorias") // Mapea las solicitudes HTTP GET a este método para obtener las categorías de libros.
    public List<String> getCategorias() {
        return libroService.findDistinctCategorias(); // Devuelve una lista de categorías distintas de los libros.
    }

    @GetMapping("/categorias/{categoria}") // Mapea las solicitudes HTTP GET que incluyen un parámetro 'categoria' a este método.
    public List<Libro> getLibrosByCategoria(@PathVariable String categoria) { 
        // Devuelve una lista de libros filtrados por una categoría específica.
        return libroService.findByCategoria(categoria); // Llama al servicio para obtener todos los libros que pertenecen a una categoría específica.
    }
}

