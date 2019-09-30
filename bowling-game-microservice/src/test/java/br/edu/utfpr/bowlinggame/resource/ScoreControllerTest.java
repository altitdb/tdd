package br.edu.utfpr.bowlinggame.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@WebMvcTest({ ScoreController.class })
class ScoreControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ScoreService scoreService;
    
    @Test
    public void shouldReturnScore() throws Exception {
        BDDMockito.when(scoreService.getScore()).thenReturn(new ScoreDTO(13));
        
        this.mvc.perform(get("/api/v1/score"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.score").value(13));
    }
}
