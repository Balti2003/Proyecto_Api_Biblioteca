async function cargarAutores() {
    // Función asíncrona para cargar la lista de autores en un elemento <select>.

    let json = await fetchAutores();
    // Llama a fetchAutores() para obtener la lista de autores desde la API y espera la respuesta.

    let select = document.getElementById("listaAutores");
    // Obtiene el elemento <select> con el ID "listaAutores".

    select.innerHTML = "";
    // Limpia el contenido actual del <select> para preparar para la carga de nuevas opciones.

    json.forEach(autor => {
        // Itera sobre cada objeto `autor` en el array `json`.
        let opcion = document.createElement("option");
        // Crea un nuevo elemento <option> para el <select>.

        opcion.value = autor.id;
        // Establece el valor del <option> al ID del autor.

        opcion.text = autor.nombre;
        // Establece el texto visible del <option> al nombre del autor.

        select.appendChild(opcion);
        // Añade el <option> al elemento <select>.
    });
}

function clickCreate() {
    // Función para crear o actualizar un libro basado en los datos del formulario.

    let libro = {
        "titulo": document.getElementById("txtTitulo").value,
        // Obtiene el título del libro del campo de entrada con el ID "txtTitulo".

        "categoria": document.getElementById("txtCategoria").value,
        // Obtiene la categoría del libro del campo de entrada con el ID "txtCategoria".

        "disponible": document.getElementById("txtDisponibilidad").value == "si",
        // Obtiene la disponibilidad del libro y lo convierte a booleano (true si el valor es "si", de lo contrario false).

        "autor": {
            "id": parseInt(document.getElementById("listaAutores").value, 10)
            // Obtiene el ID del autor seleccionado en el elemento <select> y lo convierte a entero.
        }
    };

    saveLibro(libro);
    // Llama a la función saveLibro() para guardar el libro en la base de datos.
}

function getLibroId() {
    // Función para obtener el ID del libro desde la URL actual.

    let auxSplit = window.location.href.split("?");
    // Divide la URL en partes utilizando el carácter "?" como delimitador.

    let id = auxSplit[1];
    // Toma la segunda parte del array, que debería contener el ID del libro.

    return id;
}

async function cargarLibro() {
    // Función asíncrona para cargar los datos del libro en los campos del formulario.

    if (isNew()) {
        // Verifica si el libro es nuevo.
        alert("Este es un libro nuevo");
        // Muestra un mensaje de alerta indicando que el libro es nuevo.
        return;
        // Sale de la función sin hacer nada más si el libro es nuevo.
    }

    let id = getLibroId();
    // Obtiene el ID del libro desde la URL.

    let libro = await getLibroById(id);
    // Llama a getLibroById() para obtener los datos del libro con el ID especificado.

    document.getElementById("txtTitulo").value = libro.titulo;
    // Establece el valor del campo de entrada "txtTitulo" al título del libro.

    document.getElementById("txtCategoria").value = libro.categoria;
    // Establece el valor del campo de entrada "txtCategoria" a la categoría del libro.

    document.getElementById("txtDisponibilidad").value = libro.disponible ? "si" : "no";
    // Establece el valor del campo de entrada "txtDisponibilidad" a "si" o "no" basado en la disponibilidad del libro.

    let autorId = libro.autor.id;
    // Obtiene el ID del autor del libro.

    let opciones = document.getElementById("listaAutores").options;
    // Obtiene las opciones del elemento <select> con el ID "listaAutores".

    for (let i = 0; i < opciones.length; i++) {
        // Itera sobre cada opción en el <select>.
        if (opciones[i].value === autorId.toString()) {
            // Verifica si el valor de la opción coincide con el ID del autor convertido a cadena.
            opciones[i].selected = true;
            // Marca la opción correspondiente como seleccionada.
            break;
        }
    }
}

function isNew() {
    // Función para verificar si el libro es nuevo basándose en la URL.

    let hasIdInUrl = window.location.href.includes("?");
    // Verifica si la URL contiene un carácter "?", indicando que hay un ID de libro en la URL.

    return !hasIdInUrl;
    // Devuelve true si no hay ID en la URL, lo que indica que el libro es nuevo.
}

cargarAutores();
// Llama a la función cargarAutores() para cargar la lista de autores en el elemento <select> cuando se cargue el script.

cargarLibro();
// Llama a la función cargarLibro() para cargar los datos del libro en el formulario si el libro no es nuevo.

