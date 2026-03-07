# SDET Test Automation Framework

Multi-module **Java test automation framework** with shared core utilities, a **UI automation module** and an **API automation module**.

The goal is to keep **test intent readable** while isolating infrastructure concerns such as configuration, driver lifecycle, request setup, retries, and failure diagnostics.

---

## Key characteristics

- Clear separation between framework code and test scenarios
- Shared **core module** for config, navigation, readers, and utilities
- `BasePage` as the interaction facade for Page Objects
- Thread-safe WebDriver lifecycle management
- Configuration-driven execution for browser, headless mode, window sizing, and timeouts
- Reusable API request specification with centralized base URL and JSON defaults
- Thin API client layer to keep tests readable
- Retry support and failure diagnostics for UI tests
- Allure failure attachments for faster triage

---

## Project structure 

```text
framework-core/src/main/java/framework
├── config        // Configuration access
├── data          // Models and data readers
├── navigation    // Shared URLs/endpoints
├── stability     // Failure reason model
└── utils         // Utility helpers

framework-core/src/main/resources
└── framework.properties

framework-web/src/main/java/framework
├── base          // BasePage, waits, JS/actions, browser helpers
├── driver        // Driver lifecycle, factory, options, enums
├── pages         // Page Objects
└── stability     // Web failure classification / retry policy

framework-web/src/test/java
├── framework/base        // BaseTest
├── framework/listeners   // Retry, MDC, failure artifacts
├── framework/retry       // RetryAnalyzer
├── framework/testdata    // UI test data factory
└── tests/ui              // UI scenarios

framework-web/src/test/resources
├── testng.xml
└── testdata              // CSV / JSON test data

framework-api/src/test/java
├── framework/api/client     // ApiClient, RestAssuredClient
├── framework/api/config     // API config
├── framework/api/endpoints  // Endpoint definitions
├── framework/api/http       // Request specification
├── framework/api/todo       // Domain API client
└── tests/api                // API tests
```
---
## Tech stack

- **Language:** Java 17
- **Build tool:** Maven
- **Test framework:** TestNG
- **UI automation:** Selenium WebDriver
- **API automation:** RestAssured
- **CI**: GitHub Actions
- **Reporting**: Allure
- **Logging**: SLF4J + Logback
---

## What is implemented

### UI module

- Login happy-path test
- Invalid login coverage
- Sign-out flow
- Add-to-cart scenario
- Data-driven login tests using CSV and JSON
- Retry hook and failure evidence collection

### API module

- `GET /todos` returns a list
- `POST /todos` and `GET /todos/{id}` validation flow
- Negative validation for unexpected fields
- Centralized request spec with JSON content type and request/response logging

---

## How to run
### Run all tests
``` mvn clean test ```

### Run UI smoke tests only
Smoke tests are tagged with TestNG group smoke.

```mvn -pl framework-web -am test -Dgroups=smoke```

### Run API tests
Make sure the API under test is running on the configured base URL.
Default value in `framework.properties`:
```http://localhost:3000```

Then run:

```cd framework-api```

```mvn test ```

## CI (GitHub Actions)

**Workflow**: `.github/workflows/ci.yml`

On every **push** and **pull request** to main, CI runs UI smoke tests only:

```mvn -q -U -pl framework-web -am test -Dgroups=smoke```

CI also sets `HEADLESS=true` for stable Linux runner execution.

## Reporting

- **TestNG** is used for suite execution and grouping
- **Allure** attachments are added on UI test failure
- **Surefire reports** are generated under each module’s `target` directory

---

## Project goal

This project demonstrates **SDET-style framework design**, not just isolated tests.

The focus is on:
- maintainability
- modular architecture
- reusable infrastructure
- readable test code
- configuration-driven execution
- faster failure diagnosis