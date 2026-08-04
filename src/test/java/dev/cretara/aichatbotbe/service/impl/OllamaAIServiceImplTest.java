package dev.cretara.aichatbotbe.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class OllamaAIServiceImplTest {

    @Autowired
    OllamaAIServiceImpl ollamaAIService;

    @Test
    void getGenericAnswer() {
        String question = "Who are you?";
        String ollamaAIServiceAnswer = ollamaAIService.getAnswer(question);
        log.info("ollamaAIServiceAnswer = {}", ollamaAIServiceAnswer);
        assertNotNull(ollamaAIServiceAnswer);
    }
}