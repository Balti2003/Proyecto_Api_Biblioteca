package com.example.bibliotecaApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Anotación que indica que esta es una clase de prueba para una aplicación Spring Boot. 
//Carga el contexto completo de la aplicación para pruebas.
class BibliotecaApiApplicationTests {

    @Test // Anotación que indica que el método siguiente es un caso de prueba. Se ejecutará como una prueba unitaria.
    void contextLoads() {
        // Método vacío. Su propósito es verificar que el contexto de la aplicación Spring Boot se carga correctamente.
        // Si el contexto no se carga correctamente, la prueba fallará.
    }
}

