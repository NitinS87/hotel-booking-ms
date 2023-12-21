# Hotel Room Booking Microservices Project

## Overview

This repository contains the codebase for a Hotel Room Booking microservices project. The system is divided into three main microservices: API Gateway, Booking Service, and Payment Service.

## High-Level Design (HLD)

![High-Level Design](link-to-hld-image.png)

*Insert a brief description or explanation of the high-level design.*

## Project Structure

- **API Gateway (port 8080):** Entry point for external requests, responsible for routing to internal microservices.
- **Booking Service (port 8081):** Manages user bookings, room generation, and communication with the Payment Service.
- **Payment Service (port 8083):** Simulates payment transactions and returns a transactionId to the Booking Service.

## Microservices Communication Flow

*Explain the flow of communication between microservices using diagrams or textual descriptions.*

## How to Run Locally

1. Clone the repository: `git clone https://github.com/your-username/hotel-booking-microservices.git`
2. Navigate to each microservice directory and run: `mvn spring-boot:run`

## API Documentation

### Postman Collection Screenshots

*Insert screenshots of Postman collections, including request and response examples.*

#### Endpoint 1: /booking (Booking Service)

![Booking Endpoint](link-to-booking-endpoint-screenshot.png)

*Provide a brief description of the booking endpoint and its parameters.*

#### Endpoint 2: /transaction (Payment Service)

![Transaction Endpoint](link-to-transaction-endpoint-screenshot.png)

*Describe the transaction endpoint and its input/output.*

#### Endpoint 3: /booking/{bookingId} (Booking Service)

*Insert a brief description of the new endpoint to get booking details by booking ID.*

#### Endpoint 4: /transaction/{transactionId} (Payment Service)

*Provide details about the new endpoint to get transaction details by transaction ID.*

## Dependencies

### Booking Service

- Spring Cloud Netflix Eureka Client
- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Devtools

### Payment Service

- Spring Cloud Netflix Eureka Client
- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Devtools

### API Gateway

- Spring Boot Actuator
- Spring Cloud Netflix Eureka Client
- Spring Boot Devtools

### Eureka Server

- Spring Cloud Netflix Eureka Client
- Eureka Discovery Server
- Spring Boot Devtools

*Note: Additional dependencies might be required based on your project logic.*
