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

#### Endpoint 1: /booking (Booking Service)

![Booking Endpoint](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/e149720a-39ce-45d7-895d-ad9bae304c12)

![DB](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/3572b113-d52f-488c-b6f8-6952be97ee8d)

*Provide a brief description of the booking endpoint and its parameters.*



#### Endpoint 2: /transaction (Payment Service)

![Transaction Endpoint](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/4c29c70d-2b09-4bf7-b004-a8152fa77e50)

![DB](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/6bacc07c-76d9-422a-8375-24b5bb1bdd3c)

*Describe the transaction endpoint and its input/output.*



#### Endpoint 3: /booking/{bookingId} (Booking Service)

![Booking Details](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/911660d3-44f9-47f6-8388-7bd186e6b756)

*Insert a brief description of the new endpoint to get booking details by booking ID.*



#### Endpoint 4: /transaction/{transactionId} (Payment Service)

*Provide details about the new endpoint to get transaction details by transaction ID.*
![Transaction](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/ad0e4169-b45e-402b-86b2-e81278599fb0)
![Invalid Booking Id](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/2ca0e302-d6f4-45c8-a668-01cf0846809d)
![Invalid Mode of Payment](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/223effca-05b5-4492-8ea6-4472cff7e8e3)



![Eureka Server](https://github.com/NitinS87/hotel-booking-ms/assets/80587065/0237181f-a55d-4037-9f35-897d6dc57e42)



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
