# Spring Boot Clustered Data Warehouse Service

This Spring Boot application provides an API for for Accept deal details and persist them in the DB via RESTful endpoints and persists them in the database.

## Technologies Used

- Java 17
- Spring Boot
- Maven
- Swagger UI
- MySQL

## Getting Started

### Prerequisites

- Java 17 JDK installed
- Maven installed
- MySQL database installed and running

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/mohammednasro/springboot-demo
    ```

2. Navigate to the project directory:

    ```bash
    cd springboot-demo/demo
    ```
3. Build the project:

    ```bash
    mvn clean package
    ```

4. Run the application:

    ```bash
    java -jar target/demo.jar
    ```

5. Access the Swagger UI documentation:

    Open a web browser and go to `http://localhost:8080/swagger-ui.html`.


## Usage

### API Endpoints

- `/v1/demo/add`: POST - Create a new demo
- `/v1/demo/add/all`: POST - Create a list of demos
- `/v1/clustered-data`: GET - to get all demos (pagaple)
- `/v1/demo/{id}`: GET - to get a demo with id 

### Request and Response Formats

- Request payloads and responses are in JSON format.
- See the Swagger UI documentation for detailed information on request and response formats.

## Configuration

- Database configuration: Modify `application.yaml` to configure the database connection settings.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please create a GitHub issue or submit a pull request.
