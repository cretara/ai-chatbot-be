package dev.cretara.aichatbotbe.controller;

import tools.jackson.databind.ObjectMapper;
import dev.cretara.aichatbotbe.model.Answer;
import dev.cretara.aichatbotbe.model.Question;
import dev.cretara.aichatbotbe.model.StateCapitalAnswer;
import dev.cretara.aichatbotbe.model.StateCapitalQuestion;
import dev.cretara.aichatbotbe.service.OllamaAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OllamaAIController.class)
class OllamaAIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OllamaAIService ollamaAIService;

    @Test
    void getAnswer_returnsAnswerFromService() throws Exception {
        Question question = new Question("What is Spring Boot?");
        Answer answer = new Answer("A framework for building Java applications.");
        when(ollamaAIService.getAnswer(any(Question.class))).thenReturn(answer);

        mockMvc.perform(post("/ollama/ask")
                        .header("API-Version", "1.0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answer").value("A framework for building Java applications."));

        verify(ollamaAIService).getAnswer(question);
    }

    @Test
    void getStateCapital_returnsCapitalAnswerFromService() throws Exception {
        StateCapitalQuestion stateCapitalQuestion = new StateCapitalQuestion("Italy");
        StateCapitalAnswer capitalAnswer = new StateCapitalAnswer("Rome");
        when(ollamaAIService.getStateCapital(any(StateCapitalQuestion.class))).thenReturn(capitalAnswer);

        mockMvc.perform(post("/ollama/capital")
                        .header("API-Version", "1.0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stateCapitalQuestion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capitalAnswer").value("Rome"));

        verify(ollamaAIService).getStateCapital(stateCapitalQuestion);
    }
}
