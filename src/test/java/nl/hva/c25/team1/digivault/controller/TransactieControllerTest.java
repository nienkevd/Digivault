// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TransactieController.class)
class TransactieControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TransactieService transactieService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private KlantService klantService;

    @Autowired
    public TransactieControllerTest(MockMvc mockMvc) {
        super();
        this.mockMvc = mockMvc;
    }

    @Test
    void vindTransactieOpTransactieIdHandler() {
        Transactie transactie = new Transactie(LocalDate.of(2022, 1, 25),
                LocalTime.of(12, 12, 0), 1.5);

        // service response = transactie
        Mockito.when(transactieService.vindTransactieOpTransactieId(1)).thenReturn(transactie);
        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.get("/transactie/1");

        // service response = null
        Mockito.when(transactieService.vindTransactieOpTransactieId(2)).thenReturn(null);
        MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders.get("/transactie/2");

        // ongeldige request
        MockHttpServletRequestBuilder request3 = MockMvcRequestBuilders.get("/transactie/pipo/de/kloon");

        try {

            ResultActions response = mockMvc.perform(request1);
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"koper\":null,\"verkoper\":null," +
                            "\"transactieDatum\":\"2022-01-25\",\"transactieTijd\":\"12:12:00\",\"asset\":null," +
                            "\"aantalCryptos\":1.5}"));

            response = mockMvc.perform(request2);
            response.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$").doesNotExist());

            response = mockMvc.perform(request3);
            response.andExpect(MockMvcResultMatchers.status().isNotFound());

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}