# Delivery workflow

## Branches

`main` is always releasable. Work only happens in short-lived branches:

```text
feature/<capability>    new product work
fix/<defect>            ordinary defect correction
hotfix/<defect>         urgent production correction
chore/<maintenance>     tooling or dependency maintenance
```

For this milestone use `feature/project-bootstrap`. The next likely branch is `feature/catalog-product-api`.

## Commit points for this milestone

Make these commits as the work becomes true and independently buildable:

1. `chore: initialize repository conventions` — README, ignore rules, architecture and workflow documents.
2. `build: add Maven multi-module service foundation` — parent build and runnable service.
3. `test: add catalog service API test and coverage reporting` — test and JaCoCo configuration.
4. `ci: verify project with GitHub Actions` — CI workflow and report upload.

Do not commit merely because a file was created. Commit when a reviewer can understand, test, and potentially revert the change as one unit.

## Pull request checklist

- Branch is current with `main` and has one clear purpose.
- `mvn verify` passes locally.
- API behaviour is tested; new production logic has meaningful unit tests.
- JaCoCo report has no unexpected coverage drop.
- PR description explains the user-facing behaviour, tests run, and follow-up work.
- CI is green before squash merging to `main`.

## Test layers

| Layer | Tools | When |
| --- | --- | --- |
| Unit | JUnit 5, Mockito, AssertJ | every business rule |
| Web slice | Spring MVC test support | every controller contract |
| Integration | Spring Boot Test, Testcontainers | real database/broker interaction |
| End-to-end | Docker Compose plus HTTP client | critical user journeys |

Surefire executes unit tests in the `test` phase. Failsafe will be introduced with the first integration-test class, named `*IT`, so CI can distinguish fast unit feedback from container-backed tests.
