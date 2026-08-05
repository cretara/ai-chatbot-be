package dev.cretara.aichatbotbe.controller;

import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;
import dev.cretara.aichatbotbe.model.StateCapitalAnswer;
import dev.cretara.aichatbotbe.model.StateCapitalQuestion;
import dev.cretara.aichatbotbe.service.OllamaAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ollama")
public class OllamaAIController {

    private final OllamaAIService ollamaAIService;

    public OllamaAIController(OllamaAIService ollamaAIService) {
        this.ollamaAIService = ollamaAIService;
    }

    @PostMapping(value="/ask", version = "1.0")
    public Answer getAnswer(@RequestBody Question question) {
        return ollamaAIService.getAnswer(question);
    }

    @PostMapping(value = "capital", version="1.0")
    public StateCapitalAnswer getStateCapital(@RequestBody StateCapitalQuestion stateCapitalQuestion) {
        return ollamaAIService.getStateCapital(stateCapitalQuestion);
    }

}
