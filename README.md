# Commerce Platform Microservices

The flagship project for the YouTube series: a production-style commerce platform built incrementally with Java, Spring Boot, Maven, and GitHub.

## Architecture direction

```text
clients -> api-gateway -> identity | catalog | order | inventory | payment | notification
                              \        |         |        |
                               service-owned databases, events, and observability
```

The first milestone contains only the repository foundation and a small, independently runnable `catalog-service`. Future services are added one feature branch at a time; no service reads another service's database.

## Repository layout

```text
services/       independently deployable Spring Boot services
platform/       shared infrastructure definitions (Docker, later Kubernetes)
docs/           architecture decisions and delivery workflow
.github/        continuous-integration workflows
```

## Local quick start

Prerequisites: Java 25 and Maven 3.9+.

```bash
mvn verify
mvn -pl services/catalog-service spring-boot:run
```

Then open `http://localhost:8081/swagger-ui/index.html`.

## Quality reports

```bash
mvn verify
open services/catalog-service/target/site/jacoco/index.html
```

Surefire's XML test results are in `services/catalog-service/target/surefire-reports`.

## Delivery workflow

1. Start each scoped milestone from updated `main`: `git switch main && git pull --ff-only`.
2. Create a branch, for example: `git switch -c feature/catalog-service-bootstrap`.
3. Make small logical commits; run `mvn verify` before every commit.
4. Push the branch, open a pull request into `main`, and let CI pass before merging.
5. Use squash merge for a clean `main` history, then delete the merged branch.

See [docs/delivery-workflow.md](docs/delivery-workflow.md) for the commit plan and PR checklist.
