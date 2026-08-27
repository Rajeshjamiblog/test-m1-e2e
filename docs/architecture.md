# Architecture: first increment

## Business slice

We are building a commerce platform. The eventual workflow is: a customer authenticates, browses a catalogue, places an order, inventory is reserved, payment is processed, and the customer is notified.

## Service boundaries

| Service | Owns | First delivery |
| --- | --- | --- |
| identity-service | users, roles, tokens | authentication and authorization |
| catalog-service | products and catalogue queries | bootstrap now |
| order-service | order lifecycle | after catalogue |
| inventory-service | stock reservations | after orders |
| payment-service | payment attempts | after inventory |
| notification-service | delivery of messages | event consumer |
| api-gateway | edge routing and cross-cutting concerns | after two services exist |

Each service owns its data and exposes a versioned HTTP API. Integration events will use Kafka only once an actual cross-service use case exists. This avoids introducing infrastructure before it solves a problem.

## First increment scope

`catalog-service` provides a health-style API endpoint and generated OpenAPI documentation. It has no database yet. Persistence, containers, and Testcontainers arrive in the next catalogue feature when there is a repository to test.

## Technology choices

- Java 25; Spring Boot 4.1.1; Maven 3.9+
- Spring MVC, Spring Boot Actuator, Springdoc OpenAPI 3.0.3
- JUnit 5, Mockito, AssertJ, Testcontainers, JaCoCo
- GitHub Actions for repeatable builds and report artifacts

Spring Boot 4.1.1 supports Java versions through 26. Springdoc's documented Boot 4 compatibility line is 3.x.
