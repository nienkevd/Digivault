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
        Mockito.when(transactieService.vindTransactieOpTransactieId(1)).thenReturn(transactie);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/transactie/1");
        try {
            ResultActions response = mockMvc.perform(request);
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"koper\":null,\"verkoper\":null," +
                            "\"transactieDatum\":\"2022-01-25\",\"transactieTijd\":\"12:12:00\",\"asset\":null," +
                            "\"aantalCryptos\":1.5}"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}