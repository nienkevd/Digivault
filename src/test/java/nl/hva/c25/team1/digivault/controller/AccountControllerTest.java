package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.LoginService;
import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testen bij AccountController
 *
 * @author Sezi, studentnummer 500889525
 */

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    public AccountControllerTest(MockMvc mockMvc) {
        super();
        this.mockMvc = mockMvc;
    }

    @Test
    void vindAccountOpEmailadresHandler() {
        Account annie = new Account("annie@gmail.com", "Annie7890");
        Mockito.when(accountService.vindAccountOpEmailadres("annie@gmail.com")).thenReturn(annie);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/account/annie@gmail.com");

        try {
            ResultActions response = mockMvc.perform(request);
            response
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers
                            .content()
                            .json(" {    \"emailadres\": annie@gmail.com,\n" +
                                    " \"wachtwoord\": Annie7890}"))
                    .andDo(MockMvcResultHandlers.print());

        } catch (Exception e) {
            System.out.println("O help, watskbeurt?");
        }
    }
}