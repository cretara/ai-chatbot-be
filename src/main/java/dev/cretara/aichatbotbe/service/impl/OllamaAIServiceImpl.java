package dev.cretara.aichatbotbe.service.impl;

import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;
import dev.cretara.aichatbotbe.model.StateCapitalAnswer;
import dev.cretara.aichatbotbe.model.StateCapitalQuestion;
import dev.cretara.aichatbotbe.service.OllamaAIService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class OllamaAIServiceImpl implements OllamaAIService {

    private final ChatModel chatModel;

    @Value("classpath:templates/state-template.st")
    private Resource getCapitalPrompt;

    public OllamaAIServiceImpl(@Qualifier("ollamaChatModel") ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        OllamaChatOptions ollamaChatOptions = OllamaChatOptions.builder()
                .model(OllamaModel.GEMMA)
                .build();
        Prompt questionPrompt = new Prompt(question.question(), ollamaChatOptions);
        ChatResponse questionChatResponse = chatModel.call(questionPrompt);
        return new Answer(Objects.requireNonNull(questionChatResponse.getResult()).getOutput().getText());
    }

    @Override
    public StateCapitalAnswer getStateCapital(StateCapitalQuestion stateCapitalQuestion) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        OllamaChatOptions ollamaChatOptions = OllamaChatOptions.builder()
                .model(OllamaModel.GEMMA)
                .build();
        Map<String, Object> stateOrCountryMap = Map.of("stateOrCountry", stateCapitalQuestion.capitalQuestion());
        Prompt capitalPrompt = promptTemplate.create(stateOrCountryMap, ollamaChatOptions);
        ChatResponse capitalChatResponse = chatModel.call(capitalPrompt);
        return new StateCapitalAnswer(Objects.requireNonNull(capitalChatResponse.getResult()).getOutput().getText());
    }
}
