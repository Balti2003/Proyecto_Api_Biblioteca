package com.example.bibliotecaApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.bibliotecaApi.entities.Autor;
import com.example.bibliotecaApi.entities.Libro;
import com.example.bibliotecaApi.repository.LibroRepository;
import com.example.bibliotecaApi.service.LibroService;

@RunWith(MockitoJUnitRunner.class) // Indica que se utilizará MockitoJUnitRunner para ejecutar las pruebas, facilitando la inyección de mocks en la clase de prueba.
public class LibroServiceTest {

    @InjectMocks // Anotación que indica que Mockito debe inyectar los mocks en esta instancia de LibroService.
    private LibroService libroService;

    @Mock // Anotación que indica que Mockito debe crear un mock para la instancia de LibroRepository.
    private LibroRepository libroRepository;

    @Test // Anotación que indica que el siguiente método es un caso de prueba.
    public void testFindAll() {
        // Arrange: Configuración de datos de prueba.
        Autor autor1 = new Autor(1L, "Autor1", "Pais1");
        Autor autor2 = new Autor(2L, "Autor2", "Pais2");

        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1L, "Título1", "Categoría1", true, autor1));
        libros.add(new Libro(2L, "Título2", "Categoría2", false, autor2));

        // Configura el comportamiento esperado del mock libroRepository.
        when(libroRepository.findAll()).thenReturn(libros);

        // Act: Llama al método findAll() del servicio.
        List<Libro> resultado = libroService.findAll();

        // Assert: Verifica que el resultado tenga el tamaño esperado y que el método findAll() fue llamado una vez.
        assertEquals(2, resultado.size()); // Verifica que la lista de libros contiene 2 elementos.
        verify(libroRepository, times(1)).findAll(); // Verifica que findAll() en libroRepository fue llamado exactamente una vez.
    }

    @Test // Anotación que indica que el siguiente método es un caso de prueba.
    public void testFindById() {
        // Arrange: Configuración de datos de prueba.
        Autor autor1 = new Autor(1L, "Autor1", "Pais1");
        Libro libro = new Libro(1L, "Título1", "Categoría1", true, autor1);
        
        // Configura el comportamiento esperado del mock libroRepository para cuando se busque un libro por ID.
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        // Act: Llama al método findById() del servicio.
        Optional<Libro> resultado = libroService.findById(1L);

        // Assert: Verifica que el libro devuelto es el esperado y que el método findById() fue llamado una vez con el ID proporcionado.
        assertEquals(libro, resultado.get()); // Verifica que el libro devuelto por el servicio es el libro configurado.
        verify(libroRepository, times(1)).findById(1L); // Verifica que findById() en libroRepository fue llamado exactamente una vez con el ID 1L.
    }
}

