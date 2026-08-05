package dev.cretara.aichatbotbe.service;

import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;
import dev.cretara.aichatbotbe.model.StateCapitalAnswer;
import dev.cretara.aichatbotbe.model.StateCapitalQuestion;

public interface OllamaAIService {

    Answer getAnswer(Question question);

    StateCapitalAnswer getStateCapital(StateCapitalQuestion stateCapitalQuestion);
}
