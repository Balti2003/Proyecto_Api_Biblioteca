package com.example.bibliotecaApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotecaApi.entities.Autor;
import com.example.bibliotecaApi.service.AutorService;

@CrossOrigin("*") // Permite que cualquier origen (dominio) acceda a los recursos de este controlador mediante CORS.
@RestController // Define que esta clase es un controlador REST, permitiendo manejar solicitudes HTTP y devolver respuestas en formato JSON.
@RequestMapping("/api/autores") // Mapea las solicitudes HTTP que comienzan con "/api/autores" a este controlador.
public class AutorController {

    @Autowired // Inyecta una instancia de la clase AutorService automáticamente, para usar sus métodos en este controlador.
    private AutorService autorService;

    @GetMapping // Mapea las solicitudes HTTP GET a este método, que se ejecutará cuando alguien acceda a "/api/autores" vía GET.
    public List<Autor> getAllAutores() {
        return autorService.findAll(); // Llama al servicio AutorService para obtener la lista de todos los autores y la devuelve en formato JSON.
    }
}

