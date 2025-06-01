# Proyecto de Gestión de Vuelos y Aviones ✈️
# Autor: Rafael Navarro

# Descripción

Este proyecto permite gestionar vuelos y aviones mediante una base de datos H2 en modo archivo,implementando operaciones CRUD utilizando el patrón DAO. Además, está organizado en packages según el contenido de las clases.

# Funcionalidades

- Crear, listar, buscar por ID, actualizar y eliminar tanto vuelos como aviones, interactuando con el usuario y validando las entradas.
- Manejo de excepciones en caso de incongruencias.
- Logging de operaciones con Log4j2.
- Creación de la B.D en código.
- Uso del patron DAO en combinación con el concepto de clase genérica.

# Estructura del Proyecto
Dentro de src/main/java/, se encuentra el paquete org.example/, el cual contiene lo siguiente:
- La clase Main, donde está la lógica para ingresar datos, vincularlos a las clases correspondientes y ejecutar el código.
- El paquete dao, donde se encuentran 3 clases: VuelosDAO, VuelosDAOImpl y AvionDAOimpl. Mientras que la interfaz VuelosDAO (genérica) provee los métodos que representan a las operaciones CRUD (create, read, update, delete), las otras 2 clases la implementan, usando esos mismos métodos asignándoles la clase con la que operarán (Vuelos o Avion).
-  El paquete model, que cuenta con las clases Vuelos y Avion. Estas son las clases con las que se trabaja representando objetos (un vuelo o un avion), y sus atributos serán almacenados en la base de datos en forma de filas (registros).
- El paquete util, que contiene a la clase DatabaseUtil. Esta es primordial para realizar las conexiones a la base de datos deseada, incluyendo la dirección, usuario y contraseña. Además, provee un método para cerrar las conexiones que serán utilizadas en las clases de implementación del DAO.

Sumado a esto, aparece el paquete resources, donde se encuentra el archivo log4j2.xml. Este es muy importante, ya que en él se encuentra toda la configuración necesaria para el Logger que se usa en el proyecto, que es el encargado de mostrar ciertos mensajes, tales como de información o de error

# Ejecución

1. Descargar el código.
2. Abrir el proyecto.
3. Ejecutar la clase Main.
4. Seguir las instrucciones por consola al ingresar los datos.

La base de datos H2 se crea automáticamente cuando se ejecuta el programa.

# Dependencias
Las dependencias usadas para la realización de este proyecto son:
- H2 Database
- Log4j 2


