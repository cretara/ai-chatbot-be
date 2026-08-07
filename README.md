## Project

Spring Boot 4.1.0 backend (Java 25) for an AI chatbot, using Spring AI 2.0.0 with the different LLM (Ollama, OpenAI)

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