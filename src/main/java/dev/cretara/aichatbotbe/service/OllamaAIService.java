package dev.cretara.aichatbotbe.service;

import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;

public interface OllamaAIService {

    Answer getAnswer(Question question);

}
