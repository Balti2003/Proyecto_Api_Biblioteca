const API_BASE_URL = "http://localhost:8080/api"; 
// Define la URL base para las llamadas a la API, donde todos los endpoints de la API estarán disponibles.

async function fetchFromAPI(endpoint, method = "GET", body = null) {
    // Función asíncrona para realizar una solicitud HTTP a la API.
    const config = {
        method: method, // Configura el método HTTP (GET, POST, PUT, DELETE).
        headers: {
            "Content-Type": "application/json" // Especifica que el contenido de la solicitud será JSON.
        }
    };

    if (body) {
        // Si se proporciona un cuerpo (para POST o PUT), convierte el objeto a JSON y lo añade a la configuración.
        config.body = JSON.stringify(body);
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, config);
    // Realiza la solicitud HTTP y espera la respuesta.
    return await response.json(); 
    // Convierte la respuesta en formato JSON y la devuelve.
}

async function fetchAutores() {
    // Llama a fetchFromAPI para obtener todos los autores.
    return await fetchFromAPI("/autores");
}

async function fetchLibros() {
    // Llama a fetchFromAPI para obtener todos los libros.
    return await fetchFromAPI("/libros");
}

async function deleteLibroFromBD(id) {
    // Llama a fetchFromAPI para eliminar un libro de la base de datos usando el método DELETE.
    await fetchFromAPI(`/libros/${id}`, "DELETE");
}

async function saveLibro(libro) {
    // Guarda un libro nuevo o actualiza uno existente en la base de datos.
    const url = isNew() ? "/libros" : `/libros/${getLibroId()}`;
    // Determina la URL en función de si se está creando un nuevo libro o actualizando uno existente.
    const metodoType = isNew() ? "POST" : "PUT";
    // Determina el método HTTP (POST para nuevo, PUT para actualizar).

    await fetchFromAPI(url, metodoType, libro);
    // Llama a fetchFromAPI para enviar el libro a la API.

    alert("Libro Guardado con ÉXITO");
    // Muestra un mensaje de éxito.
    window.location.href = "tables.html";
    // Redirige al usuario a la página "tables.html".
}

async function getLibroById(id) {
    // Obtiene un libro específico por su ID.
    return await fetchFromAPI(`/libros/${id}`);
}

async function getCategorias() {
    // Obtiene todas las categorías disponibles de libros.
    return await fetchFromAPI("/libros/categorias");
}

async function getLibrosByAutor(idAutor) {
    // Obtiene todos los libros de un autor específico.
    return await fetchFromAPI(`/libros/autor/${idAutor}`);
}

async function getLibrosPorCategoria(categoria) {
    // Obtiene todos los libros de una categoría específica.
    return await fetchFromAPI(`/libros/categorias/${categoria}`);
}

