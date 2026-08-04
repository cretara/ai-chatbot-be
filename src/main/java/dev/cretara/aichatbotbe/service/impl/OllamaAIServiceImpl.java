package dev.cretara.aichatbotbe.service.impl;

import dev.cretara.aichatbotbe.service.OllamaAIService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
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
    public String getAnswer(String question) {
        OllamaChatOptions ollamaChatOptions = OllamaChatOptions.builder()
                .temperature(0.1)
                .build();
        Prompt prompt = new Prompt(question, ollamaChatOptions);
        ChatResponse chatResponse = chatModel.call(prompt);
        return Objects.requireNonNull(chatResponse.getResult()).getOutput().getText();
    }
}
