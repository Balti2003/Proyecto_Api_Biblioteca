package com.example.bibliotecaApi;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.bibliotecaApi.controller.LibroController;
import com.example.bibliotecaApi.entities.Autor;
import com.example.bibliotecaApi.entities.Libro;
import com.example.bibliotecaApi.service.LibroService;

@RunWith(MockitoJUnitRunner.class) // Indica que se utilizará MockitoJUnitRunner para ejecutar las pruebas. Esto permite la inyección automática de mocks en la clase de prueba.
public class LibroControllerTest {

    @InjectMocks // Anotación que indica que Mockito debe inyectar los mocks en esta instancia de LibroController.
    private LibroController libroController;

    @Mock // Anotación que indica que Mockito debe crear un mock para la instancia de LibroService.
    private LibroService libroService;

    @SuppressWarnings("deprecation") // Suprime advertencias sobre el uso de métodos obsoletos.
    @BeforeEach // Anotación que indica que este método se ejecutará antes de cada caso de prueba.
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa los mocks anotados en la clase de prueba.
    }

    @Test // Anotación que indica que el método siguiente es un caso de prueba.
    public void testGetAllLibros() {
        // Configuración de prueba: crea un autor y una lista de libros que serán devueltos por el mock de LibroService.
        Autor autor1 = new Autor(1L, "NOMBRE1", "PAIS1");
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1L, "Titulo 1", "categoria1", true, autor1));
        libros.add(new Libro(2L, "Titulo 2", "categoria2", true, autor1));

        // Configuración del comportamiento esperado del mock libroService.
        when(libroService.findAll()).thenReturn(libros);

        // Llama al método getAllLibros() del controlador.
        List<Libro> resultado = libroController.getAllLibros();

        // Verifica que el resultado de getAllLibros() tenga 2 libros.
        assertEquals(2, resultado.size());

        // Verifica que el método findAll() del mock libroService fue llamado exactamente una vez.
        verify(libroService, times(1)).findAll();
    }
}

