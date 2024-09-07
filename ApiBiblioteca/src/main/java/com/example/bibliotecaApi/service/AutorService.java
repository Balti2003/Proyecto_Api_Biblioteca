package com.example.bibliotecaApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaApi.entities.Autor;
import com.example.bibliotecaApi.repository.AutorRepository;

@Service // Marca esta clase como un servicio, un componente de la capa de lógica de negocio en la aplicación.
public class AutorService {

    @Autowired // Inyecta automáticamente una instancia del repositorio AutorRepository para que pueda ser utilizada en los métodos de esta clase.
    private AutorRepository autorRepository;

    public List<Autor> findAll() {
        // Llama al método findAll() del repositorio para obtener todos los autores de la base de datos.
        // Devuelve una lista con todos los autores.
        return autorRepository.findAll();
    }
}

