# Lab4-El-Buen-Sabor
Trabajo final de la materia de Laboratorio 4 UTN.

#Descripción
Aplicación para delivery que ofrece a sus clientes una amplia variedad de bebidas y de comidas de fabricación propia, 
posee un horario de atención de lunes a domingos de 20:00 a 12:00, y de sábados y domingos de 11:00 a 15:00. 
Los clientes tienen a disposición un menú que describe para cada una de las comidas, el nombre, 
los ingredientes y el precio. Los clientes realizan sus pedidos en el mostrador del local 
mediante una PC o pueden hacerlo en forma remota desde su casa o su celular personal.

## Tecnologías que se usan en la aplicación.
> La aplicación cuenta con una Api Rest propia que está desarrollada a nivel backend en java con el framework Spring Boot.
> Mientras que el frontend está hecho con el framework de Angular.
> El desarrollo está hecho con el lenguaje SQL para base de datos relacionales, con Mysql para la gestión.

### Recursos, paquetes o librerías utilizadas:
1. Estilos de [Bootstrap](https://getbootstrap.com).
2. Implementación para crear los token de autenticación con [JWT](https://jwt.io).
3. Se registró la documentación en [Swagger](https://swagger.io).
4. Iconos de [Fontawesome](https://fontawesome.com). 
5. Animaciones de [Animate.css](https://animate.style).
6. Otros paquetes y librerías son: Project Lombok, ItextPdf, Thymeleaf, SweetAlert2, Toastr, Ngx-Spinner, WebSocket.
7. Se usó de la Api de Mercado Pago.

### Deploy en Heroku y Netlify
* [Api Rest](https://buensabor-api.herokuapp.com), se debe acceder mediantes login y el token creado para los distintos endpoint.
* [App](https://buensabor.netlify.app).
* [Administración](https://buensabor-admin.netlify.app).