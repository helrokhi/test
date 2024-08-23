package ru.testing.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.testing.dto.TestingDto;
import ru.testing.service.TestingService;

import java.util.LinkedHashMap;
import java.util.Map;

@WebMvcTest(TestingController.class)
class TestingControllerTest {
    @MockBean
    private TestingService testingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getResult() throws Exception {
        String input = "aaaaabcccc";
        Map<Character, Integer> result = new LinkedHashMap<>();
        result.put('a', 5);
        result.put('c', 4);
        result.put('b', 1);

        TestingDto testingDto = TestingDto.builder()
                .result(result)
                .build();

        Mockito.when(testingService.getResult(input))
                .thenReturn(testingDto);

        String testingJson = objectMapper.writeValueAsString(testingDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/testing")
                                .param("input", input)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(testingJson));


        Mockito.verify(testingService, Mockito.times(1))
                .getResult(input);
    }
}