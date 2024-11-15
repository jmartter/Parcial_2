# Proyecto: Parcial 2

## Enlace del repositorio 

[Repositorio](https://github.com/jmartter/Parcial_2.git)

Proyecto realizado por Jose Daniel Martín y Hugo Sanchez

Este proyecto es una aplicación basada en Spring Boot que utiliza tecnologías como Project Reactor, RabbitMQ, y bases de datos relacionales para procesar y manejar datos provenientes de un archivo CSV. También incluye una interfaz web para visualizar las notificaciones procesadas.

## Índice
1. [Clases y Métodos](#clases-y-métodos)
    - [AppConfig](#appconfig)
    - [NotificationController](#notificationcontroller)
    - [ValorNormal](#valornormal)
    - [ValorNormalRepository](#valornormalrepository)
    - [RabbitMQConfig](#rabbitmqconfig)
    - [RabbitMQListener](#rabbitmqlistener)
    - [CsvController](#csvcontroller)
    - [CsvService](#csvservice)
    - [Parcial2Application](#parcial2application)
2. [Interfaz Web: `index.html`](#interfaz-web-indexhtml)
3. [Tecnologías Usadas](#tecnologías-usadas)
4. [Endpoints REST](#endpoints-rest)

---

## Clases y Métodos

### AppConfig
Clase de configuración que define beans para la aplicación.
- **`@Bean public RestTemplate restTemplate()`**  
  Crea y registra un bean de `RestTemplate` para realizar solicitudes HTTP desde la aplicación.

---

### NotificationController
Controlador REST para manejar notificaciones.
- **`@PostMapping("/notify") public void receiveNotification(@RequestBody String message)`**  
  Recibe un mensaje y lo añade a una lista de notificaciones.

- **`@GetMapping("/notifications") public List<String> getNotifications()`**  
  Devuelve todas las notificaciones almacenadas.

---

### ValorNormal
Entidad JPA que representa un valor numérico.
- **`private Long id`**  
  ID único de la entidad.

- **`private Double valor`**  
  Valor numérico almacenado.

---

### ValorNormalRepository
Repositorio JPA para operaciones con la entidad `ValorNormal`.
- **`@Modifying @Transactional public void truncateTable()`**  
  Trunca la tabla `valor_normal` para eliminar todos los registros.

---

### RabbitMQConfig
Clase de configuración para RabbitMQ.
- **`@Bean public Queue csvQueue()`**  
  Define una cola duradera llamada `csvQueue`.

---

### RabbitMQListener
Componente que escucha mensajes de RabbitMQ.
- **`@RabbitListener(queues = "csvQueue") public void subscribeToCsvQueue(String message)`**  
  Escucha mensajes de la cola `csvQueue` y registra logs de los mensajes recibidos. También reenvía los mensajes a un endpoint HTTP usando `RestTemplate`.

---

### CsvController
Controlador REST para operaciones relacionadas con el procesamiento del archivo CSV.
- **`@GetMapping("/load-csv") public Flux<ValorNormal> loadCsv()`**  
  Carga datos del archivo CSV y los publica como un flujo reactivo.

- **`@PostMapping("/pause-csv") public void pauseCsv()`**  
  Pausa el procesamiento del archivo CSV.

- **`@PostMapping("/resume-csv") public void resumeCsv()`**  
  Reanuda el procesamiento del archivo CSV.

---

### CsvService
Servicio para el manejo del archivo CSV.
- **`public Flux<ValorNormal> publishCsvData()`**  
  Lee un archivo CSV, almacena los datos en la base de datos, y los publica como un flujo reactivo. Además, envía mensajes a la cola `csvQueue`.

- **`public void pause()`**  
  Pausa el procesamiento del archivo CSV.

- **`public void resume()`**  
  Reanuda el procesamiento del archivo CSV.

---

### Parcial2Application
Clase principal que inicia la aplicación.
- **`public static void main(String[] args)`**  
  Método principal para ejecutar la aplicación Spring Boot.

- **`@Bean public CommandLineRunner run()`**  
  Ejecuta la carga de datos del archivo CSV al iniciar la aplicación.

---

## Interfaz Web: `index.html`

La interfaz web permite visualizar las notificaciones procesadas y proporciona controles para pausar y reanudar el procesamiento del archivo CSV.

### Estructura Principal
- **Contenedor de notificaciones:**  
  - Un div con scroll para mostrar las notificaciones recibidas.
  - Las notificaciones se obtienen periódicamente mediante llamadas al endpoint `/notifications`.

- **Botones de control:**  
  - Botón `Pause` que llama al endpoint `/pause-csv` para pausar la carga del CSV.
  - Botón `Resume` que llama al endpoint `/resume-csv` para reanudar la carga del CSV.

- **Visualización D3.js:**  
  Una representación gráfica interactiva de las notificaciones como bolas que caen dentro de contenedores.

### Estilos y Funcionalidades
- **Estilo:**  
  Utiliza un diseño moderno con CSS, incluyendo bordes redondeados, sombras y un diseño responsivo.
  
- **JavaScript:**  
  - `fetchNotifications()`: Obtiene notificaciones del backend y actualiza la interfaz.
  - `dropBall(value)`: Genera animaciones D3.js para representar las notificaciones como bolas.

- **Automatización:**  
  Las notificaciones se obtienen automáticamente cada 20 ms utilizando `setInterval`.

---

## Tecnologías Usadas
- **Spring Boot**: Para crear y gestionar la aplicación.
- **Project Reactor**: Para manejar flujos de datos reactivos.
- **RabbitMQ**: Para la mensajería entre servicios.
- **JPA**: Para el manejo de datos en la base de datos relacional.
- **OpenCSV**: Para la lectura del archivo CSV.
- **D3.js**: Para las visualizaciones interactivas en la interfaz web.

---


## Endpoints REST
- **`GET /notifications`**: Devuelve las notificaciones.
- **`POST /notify`**: Envía una notificación.
- **`GET /load-csv`**: Carga datos desde el CSV.
- **`POST /pause-csv`**: Pausa la carga del CSV.
- **`POST /resume-csv`**: Reanuda la carga del CSV.
