// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.service.TransactieService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class TransactieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Transactie transactie;

    @MockBean
    private TransactieService transactieService;

    @Autowired
    public TransactieControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void vindTransactieOpTransactieIdHandler() {
        // geef aan wat de service als teruggeefwaarde heeft
        Mockito.when(transactieService.vindTransactieOpTransactieId(1)).thenReturn(transactie);
        // Maak een gesimuleerde request aan
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/transactie/{koper}", 1);
        // Stuur deze request naar de controller via de MockMvc (try-catch) en ontvang de response
        try {
            ResultActions response = mockMvc.perform(request);
            // Test of de response overeenkomt met de verwachting
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}