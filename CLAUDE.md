# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Spring Boot 4.1.0 backend (Java 25) for an AI chatbot, using Spring AI 2.0.0 with the OpenAI chat model starter. Package root: `dev.cretara.aichatbotbe`.

## Commands (Windows: use `mvnw.cmd`)

```
mvnw.cmd clean compile          # build
mvnw.cmd test                   # run all tests
mvnw.cmd test -Dtest=OpenAIServiceImplTest#getAnswer   # run a single test
mvnw.cmd spring-boot:run        # run the app
```

Native image (GraalVM 25+ required):
```
mvnw.cmd native:compile -Pnative
mvnw.cmd test -PnativeTest
```

## Configuration

- `application.properties` sets only `spring.application.name`.
- `application-local.properties` (the `local` profile) sets `spring.ai.openai.api-key=${OPEN_API_KEY}` — an `OPEN_API_KEY` env var must be set to run or test against the real OpenAI API.
- `OpenAIServiceImplTest` is a full `@SpringBootTest` activating the `local` profile and hitting the live model, so it requires `OPEN_API_KEY` to pass.

## Architecture

Minimal service-layer wrapper around Spring AI's `ChatModel`:
- `OpenAIService` — interface, single method `getAnswer(String question)`.
- `OpenAIServiceImpl` — builds a `Prompt` from the raw question text via `PromptTemplate` and delegates to the injected `ChatModel` (auto-configured by `spring-ai-starter-model-openai` from the `spring.ai.openai.*` properties). No controller/web layer exists yet — `spring-boot-starter-webmvc` is on the classpath but unused so far.

Lombok is available (annotation processor wired into both compile and test-compile in `pom.xml`) but not yet used in source.
