# Java Hello World Project

This project is a simple Java application that prints "Hello, World!" to the console. It is structured to be built and run using Maven and Docker.

## Project Structure

```
java-hello-world
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── HelloWorld.java
├── Dockerfile
├── pom.xml
└── README.md
```

## Prerequisites

- Java Development Kit (JDK) installed on your machine.
- Maven installed on your machine.
- Docker installed on your machine.

## Building the Application

To build the application, navigate to the project directory and run:

```
mvn clean package
```

This command will compile the Java code and package it into a JAR file.

## Running the Application

To run the application, you can use the following command:

```
java -cp target/java-hello-world-1.0-SNAPSHOT.jar com.example.HelloWorld
```

## Running with Docker

To build the Docker image, run the following command in the project directory:

```
docker build -t java-hello-world .
```

To run the Docker container, use:

```
docker run java-hello-world
```

This will execute the application inside a Docker container.