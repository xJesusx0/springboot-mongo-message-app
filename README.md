# SpringBoot MongoDB message app

## Descripción

Este proyecto es una API REST construida con Spring Boot que permite gestionar mensajes usando MongoDB como base de datos. La API ofrece funcionalidades para agregar y obtener mensajes.

## Funcionalidades

- **Agregar Mensaje**: Permite añadir un nuevo mensaje con un nombre de usuario y contenido.
- **Obtener Mensajes**: Recupera todos los mensajes almacenados en la base de datos MongoDB.

## Tecnologías

- **Java**: Lenguaje de programación utilizado.
- **Spring Boot**: Framework para desarrollar la API REST.
- **MongoDB**: Base de datos NoSQL utilizada para almacenar los mensajes.
- **Maven**: Herramienta de gestión de proyectos y dependencias.

## Requisitos

- **Java 11 o superior**
- **MongoDB**: Debe estar instalado y en funcionamiento en `mongodb://localhost:27017`.

## Configuración

1. **Clonar el Repositorio**

   ```bash
   git clone https://github.com/xJesusx0/springboot-mongo-message-app.git
   ```

2. **Navegar al Directorio del Proyecto**

   ```bash
   cd springboot-mongo-message-app
   ```

3. **Construir el Proyecto**

   Asegúrate de tener Maven instalado. Luego, construye el proyecto con el siguiente comando:

   ```bash
   mvn clean install
   ```

4. **Configurar MongoDB**

   Asegúrate de que MongoDB esté en funcionamiento en la dirección `mongodb://localhost:27017`. Puedes cambiar la URL de conexión en el código si es necesario.

5. **Ejecutar la Aplicación**

   Ejecuta la aplicación Spring Boot usando Maven:

   ```bash
   mvn spring-boot:run
   ```

   La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

- **GET /**: Verifica que la API está funcionando correctamente. Responde con `{ "response": "ok" }`.

- **GET /get-messages**: Obtiene todos los mensajes almacenados en MongoDB. Responde con una lista de documentos JSON.

- **POST /add-message**: Añade un nuevo mensaje. Requiere un cuerpo JSON con los campos `username` y `message`. Responde con `{ "response": "ok" }` si el mensaje se añadió correctamente.

  **Ejemplo de solicitud:**

  ```json
  {
    "username": "john_doe",
    "content": "Hello, MongoDB!"
  }
  ```

## Ejemplos de Uso

**Agregar un Mensaje:**

```bash
curl -X POST http://localhost:8080/add-message -H "Content-Type: application/json" -d '{"username": "john_doe", "content": "Hello, MongoDB!"}'
```

**Obtener Mensajes:**

```bash
curl http://localhost:8080/get-messages
```

## Clientes 
En el directorio `clients/python/` se encuentra un cliente en Python que interactúa con la API.

**Funcionalidades:**

- Conecta con la API.
- Permite al usuario ingresar un nombre de usuario y mensajes.
- Envía mensajes a la API y muestra los mensajes almacenados.

**Uso:**
```bash
python clients/python/client.py
```