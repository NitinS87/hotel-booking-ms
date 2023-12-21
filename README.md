# Hotel Room Booking Microservices Project

## Overview

This repository contains the codebase for a Hotel Room Booking microservices project. The system is divided into three main microservices: API Gateway, Booking Service, and Payment Service.

## High-Level Design (HLD)

![image](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/601c40f4-7760-4ef7-acc7-15281c86b94a)



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

![Booking Endpoint](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/9b953628-5c3f-4044-8723-ead39ca7dbc1)
![DB](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/399478ff-ec41-4988-84ca-9bb3bafd10ee)



*Provide a brief description of the booking endpoint and its parameters.*

#### Endpoint 2: /transaction (Payment Service)

![Transaction Endpoint](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/e73d8a68-1c12-4723-a123-ba97accba68a)
![DB](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/4f359d3e-3a01-41cc-8d22-27733f9285b3)


*Describe the transaction endpoint and its input/output.*

#### Endpoint 3: /booking/{bookingId} (Booking Service)
![Booking Details](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/7877c846-39e5-4ad4-8b92-54669323fb95)


*Insert a brief description of the new endpoint to get booking details by booking ID.*

#### Endpoint 4: /transaction/{transactionId} (Payment Service)

*Provide details about the new endpoint to get transaction details by transaction ID.*
![Transaction](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/ad9228c7-9da3-439a-adda-b79f6140510d)

![Eureka Server](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/2192b06d-5411-4826-9c75-b621530cd69c)


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

## Contributors

- Nitin Sharma(https://github.com/NitinS87) - 20CSU074
- Neeraj Yadav (https://github.com/contributor-1) - 20CSU070
- Niharika Joshi (https://github.com/contributor-2) - 20CSU071
- Raghav Jindal (https://github.com/contributor-2) - 20CSU084
