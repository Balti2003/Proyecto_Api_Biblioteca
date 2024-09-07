async function listarLibros() {
    // Función asíncrona para listar todos los libros y mostrarlos en una tabla HTML.

    let json = await fetchLibros();
    // Llama a la función fetchLibros() para obtener la lista de libros desde la API y espera la respuesta.

    let html = "";
    // Inicializa una variable para construir el contenido HTML de la tabla.

    json.forEach(libro => {
        // Itera sobre cada objeto `libro` en el array `json`.
        html += `<tr>
        <td>${libro.titulo}</td>
        <td>${libro.categoria}</td>
        <td>${libro.autor.nombre}</td>
        <td>${libro.disponible ? "si" : "no"}</td>
        <td>
            <a href="#" onclick="editLibro(${libro.id})" class="btn btn-primary btn-icon-split">
                <span class="text">Editar</span>
            </a>
            <a href="#" onclick="deleteLibro(${libro.id})" class="btn btn-danger btn-icon-split">
                <span class="text">Eliminar</span>
            </a>
        </td>
    </tr>`;
        // Construye una fila de tabla HTML para cada libro, incluyendo su título, categoría, autor, disponibilidad, y enlaces para editar y eliminar.
    });

    document.getElementById("tablaLibros").outerHTML = html;
    // Actualiza el contenido del elemento con el ID "tablaLibros" con el HTML generado.
}

async function deleteLibro(id) {
    // Función asíncrona para eliminar un libro basado en el ID proporcionado.

    let confirmaEliminar = confirm("Desea eliminar el libro?");
    // Muestra un cuadro de confirmación para verificar si el usuario desea eliminar el libro.

    if (confirmaEliminar) {
        // Si el usuario confirma la eliminación:
        await deleteLibroFromBD(id);
        // Llama a deleteLibroFromBD() para eliminar el libro de la base de datos.
        listarLibros();
        // Vuelve a listar todos los libros para reflejar la eliminación en la interfaz de usuario.
    }
}

async function editLibro(id) {
    // Función asíncrona para redirigir a la página de edición de un libro basado en el ID proporcionado.

    window.location.href = "gestionar-libro.html?" + id;
    // Redirige al usuario a "gestionar-libro.html", añadiendo el ID del libro como parámetro de consulta en la URL.
}

async function listarCategorias() {
    // Función asíncrona para listar todas las categorías y mostrarlas como botones.

    let categoria = await getCategorias();
    // Llama a getCategorias() para obtener la lista de categorías desde la API y espera la respuesta.

    let container = document.getElementById("categoria-container");
    // Obtiene el elemento con el ID "categoria-container" donde se mostrarán los botones de categorías.

    container.innerHTML = "";
    // Limpia el contenido actual del contenedor para preparar para la carga de nuevas categorías.

    categoria.forEach(categoria => {
        // Itera sobre cada categoría en el array `categoria`.
        let buttonHtml =
            ` <div class="text-right" style="display: inline-block;">
                    <a href="tables.html?categoria=${categoria}" class="btn btn-primary btn-icon-split d-flex btn-margin">                            
                    </span>
                        <span class="text">${categoria}</span>
                    </a>
                </div>`;
        // Construye el HTML para un botón de categoría que redirige a "tables.html" con la categoría como parámetro de consulta en la URL.
        container.innerHTML += buttonHtml;
        // Añade el HTML del botón al contenedor.
    });
}

document.addEventListener("DOMContentLoaded", listarCategorias);
// Registra un manejador de eventos para que la función listarCategorias() se ejecute cuando el contenido del DOM se haya cargado completamente.

async function listarLibrosPorAutor(idAutor) {
    // Función asíncrona para listar los libros de un autor específico.

    let json = await getLibrosByAutor(idAutor);
    // Llama a getLibrosByAutor() para obtener la lista de libros del autor con el ID proporcionado.

    let html = "";
    // Inicializa una variable para construir el contenido HTML de la tabla.

    json.forEach(libro => {
        // Itera sobre cada objeto `libro` en el array `json`.
        html += ` <tr>
            <td>${libro.titulo}</td>
            <td>${libro.categoria}</td>
            <td>${libro.autor.nombre}</td>
            <td>${libro.disponible ? "si" : "no"}</td>
            <td>
                <a href="#" onclick="editLibro(${libro.id})" class="btn btn-primary btn-icon-split">
                    <span class="text">Editar</span>
                </a>
                <a href="#" onclick="deleteLibro(${libro.id})" class="btn btn-danger btn-icon-split">
                    <span class="text">Eliminar</span>
                </a>
            </td>
        </tr>`;
        // Construye una fila de tabla HTML para cada libro, incluyendo su título, categoría, autor, disponibilidad, y enlaces para editar y eliminar.
    });
    document.getElementById("tablaLibros").innerHTML = html;
    // Actualiza el contenido del elemento con el ID "tablaLibros" con el HTML generado.
}

async function listarLibrosPorCategoria(categoria) {
    // Función asíncrona para listar los libros de una categoría específica.

    let json = await getLibrosPorCategoria(categoria);
    // Llama a getLibrosPorCategoria() para obtener la lista de libros de la categoría proporcionada.

    let html = "";
    // Inicializa una variable para construir el contenido HTML de la tabla.

    json.forEach(libro => {
        // Itera sobre cada objeto `libro` en el array `json`.
        html += ` <tr>
            <td>${libro.titulo}</td>
            <td>${libro.categoria}</td>
            <td>${libro.autor.nombre}</td>
            <td>${libro.disponible ? "si" : "no"}</td>
            <td>
                <a href="#" onclick="editLibro(${libro.id})" class="btn btn-primary btn-icon-split">
                    <span class="text">Editar</span>
                </a>
                <a href="#" onclick="deleteLibro(${libro.id})" class="btn btn-danger btn-icon-split">
                    <span class="text">Eliminar</span>
                </a>
            </td>
        </tr>`;
        // Construye una fila de tabla HTML para cada libro, incluyendo su título, categoría, autor, disponibilidad, y enlaces para editar y eliminar.
    });
    document.getElementById("tablaLibros").innerHTML = html;
    // Actualiza el contenido del elemento con el ID "tablaLibros" con el HTML generado.
}

function getIdFromUrl() {
    // Función para obtener el ID del autor desde los parámetros de la URL.

    const urlParams = new URLSearchParams(window.location.search);
    // Crea un objeto URLSearchParams para analizar los parámetros de la URL.

    const idAutor = urlParams.get('idAutor');
    // Obtiene el valor del parámetro 'idAutor' de la URL.

    return idAutor;
}

function verLibrosPorAutor(idAutor) {
    // Función para redirigir a la página de libros filtrados por autor.

    window.location.href = "tables.html?idAutor=" + idAutor;
    // Redirige al usuario a "tables.html", añadiendo el ID del autor como parámetro de consulta en la URL.
}

function getCategoriaFromUrl() {
    // Función para obtener la categoría desde los parámetros de la URL.

    const urlParams = new URLSearchParams(window.location.search);
    // Crea un objeto URLSearchParams para analizar los parámetros de la URL.

    const categoria = urlParams.get('categoria');
    // Obtiene el valor del parámetro 'categoria' de la URL.

    return categoria;
}

// Obtiene el valor de los parámetros de la URL para autor o categoría.
const categoria = getCategoriaFromUrl();
const idAutor = getIdFromUrl();

// Dependiendo de si hay un ID de autor o una categoría en la URL, llama a la función correspondiente para listar los libros.
if (idAutor) {
    listarLibrosPorAutor(idAutor);
} else if (categoria) {
    listarLibrosPorCategoria(categoria);
} else {
    listarLibros();
}

