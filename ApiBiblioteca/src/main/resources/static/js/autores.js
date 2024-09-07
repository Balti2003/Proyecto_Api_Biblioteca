async function listarAutores() {
    // Función asíncrona para listar todos los autores y mostrarlos en una tabla HTML.

    let json = await fetchAutores();
    // Llama a la función fetchAutores() para obtener la lista de autores desde la API y espera la respuesta.

    let html = "";
    // Inicializa una variable para construir el contenido HTML de la tabla.

    json.forEach(autor => {
        // Itera sobre cada objeto `autor` en el array `json`.
        html += ` <tr>
            <td>${autor.nombre}</td>
            <td>${autor.pais}</td>
            <td><a href="#" onclick="onClickVerLibros(${autor.id})" class="btn btn-primary btn-icon-split">
                                                <span class="text">Ver libros</span>
                                            </a></td>
        </tr>`;
        // Construye una fila de tabla HTML para cada autor, incluyendo su nombre, país y un enlace para ver los libros del autor.
        // El enlace tiene un manejador de clics que llama a `onClickVerLibros(id)` con el ID del autor.
    });

    document.getElementById("tablaAutor").outerHTML = html;
    // Actualiza el contenido del elemento con el ID "tablaAutor" con el HTML generado.
    // Aquí se asume que "tablaAutor" es el contenedor donde se debe mostrar la tabla de autores.
}

async function onClickVerLibros(id) {
    // Función asíncrona que maneja el clic en el enlace para ver los libros de un autor.

    window.location.href = "tables.html?idAutor=" + id;
    // Redirige al usuario a "tables.html", pasando el ID del autor como parámetro de consulta en la URL.
}

listarAutores();
// Llama a la función listarAutores() para ejecutar el proceso de obtención y visualización de autores.
