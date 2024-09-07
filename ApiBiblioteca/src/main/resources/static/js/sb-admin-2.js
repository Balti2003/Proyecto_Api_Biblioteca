(function($) {
  "use strict"; // Activa el modo estricto para evitar errores comunes y facilitar la depuración.

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    // Añade un manejador de eventos para los clics en los elementos con ID 'sidebarToggle' y 'sidebarToggleTop'.
    $("body").toggleClass("sidebar-toggled");
    // Alterna la clase "sidebar-toggled" en el elemento <body>.

    $(".sidebar").toggleClass("toggled");
    // Alterna la clase "toggled" en el elemento con clase "sidebar".

    if ($(".sidebar").hasClass("toggled")) {
      // Si el elemento con clase "sidebar" tiene la clase "toggled":
      $('.sidebar .collapse').collapse('hide');
      // Cierra todos los elementos colapsables dentro de la barra lateral.
    }
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    // Añade un manejador de eventos para el cambio de tamaño de la ventana.
    if ($(window).width() < 768) {
      // Si el ancho de la ventana es menor a 768 píxeles:
      $('.sidebar .collapse').collapse('hide');
      // Cierra todos los elementos colapsables dentro de la barra lateral.
    }
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      // Si el ancho de la ventana es menor a 480 píxeles y la barra lateral no tiene la clase "toggled":
      $("body").addClass("sidebar-toggled");
      // Añade la clase "sidebar-toggled" al elemento <body>.

      $(".sidebar").addClass("toggled");
      // Añade la clase "toggled" al elemento con clase "sidebar".

      $('.sidebar .collapse').collapse('hide');
      // Cierra todos los elementos colapsables dentro de la barra lateral.
    }
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    // Añade un manejador de eventos para el desplazamiento del ratón en la barra lateral cuando está en un contenedor con la clase "fixed-nav".
    if ($(window).width() > 768) {
      // Si el ancho de la ventana es mayor a 768 píxeles:
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      // Obtiene la dirección del desplazamiento del ratón.

      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      // Ajusta el desplazamiento de la barra lateral en función de la dirección del desplazamiento.

      e.preventDefault();
      // Previene el comportamiento de desplazamiento predeterminado.
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    // Añade un manejador de eventos para el desplazamiento del documento.
    var scrollDistance = $(this).scrollTop();
    // Obtiene la distancia del desplazamiento actual desde la parte superior del documento.

    if (scrollDistance > 100) {
      // Si la distancia del desplazamiento es mayor a 100 píxeles:
      $('.scroll-to-top').fadeIn();
      // Muestra el botón para volver al inicio con un efecto de desvanecimiento.
    } else {
      $('.scroll-to-top').fadeOut();
      // Oculta el botón para volver al inicio con un efecto de desvanecimiento.
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    // Añade un manejador de eventos para los clics en los enlaces con clase "scroll-to-top".
    var $anchor = $(this);
    // Obtiene el elemento clicado.

    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    // Anima el desplazamiento suave hacia la parte superior del elemento con el ID especificado en el atributo 'href' del enlace.
    e.preventDefault();
    // Previene el comportamiento de clic predeterminado del enlace.
  });

})(jQuery); // Finaliza la función autoinvocada, asegurando que el código no interfiera con otras bibliotecas o scripts.

