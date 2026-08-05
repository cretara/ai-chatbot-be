package dev.cretara.aichatbotbe.service.impl;

import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;
import dev.cretara.aichatbotbe.service.OllamaAIService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OllamaAIServiceImpl implements OllamaAIService {

    private final ChatModel chatModel;

    public OllamaAIServiceImpl(@Qualifier("ollamaChatModel") ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        OllamaChatOptions ollamaChatOptions = OllamaChatOptions.builder()
                .model(OllamaModel.GEMMA)
                .temperature(0.1)
                .build();
        Prompt prompt = new Prompt(question.question(), ollamaChatOptions);
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(Objects.requireNonNull(chatResponse.getResult()).getOutput().getText());
    }
}
