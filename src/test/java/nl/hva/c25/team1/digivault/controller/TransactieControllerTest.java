//// gemaakt door Anthon van Dijk
//
//package nl.hva.c25.team1.digivault.controller;
//
//import nl.hva.c25.team1.digivault.authentication.TokenService;
//import nl.hva.c25.team1.digivault.model.Account;
//import nl.hva.c25.team1.digivault.model.Klant;
//import nl.hva.c25.team1.digivault.model.Transactie;
//import nl.hva.c25.team1.digivault.service.AccountService;
//import nl.hva.c25.team1.digivault.service.KlantService;
//import nl.hva.c25.team1.digivault.service.TransactieService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
////@AutoConfigureMockMvc
////@SpringBootTest
//@WebMvcTest(TransactieController.class)
//class TransactieControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private Transactie transactie;
//
//    @Mock
//    private Klant klant;
//
//    @Mock
//    private Account account;
//
//    @MockBean
//    private TransactieService transactieService;
//
//    @MockBean
//    private TokenService tokenService;
//
//    @MockBean
//    private AccountService accountService;
//
//    @MockBean
//    private KlantService klantService;
//
//    @Autowired
//    public TransactieControllerTest(MockMvc mockMvc) {
//        this.mockMvc = mockMvc;
//    }
//
//    @BeforeEach
//    void setUp() {
//        Mockito.when(tokenService.getEmailadresToken("")).thenReturn("fakeadres@hva.nl");
//        Mockito.when(klantService.vindKlantOpEmail("fakeadres@hva.nl")).thenReturn(klant);
//        Mockito.when(klant.getTransactiepartijId()).thenReturn(10);
//        Mockito.when(tokenService.valideerJWT(anyString())).thenReturn(true);
//        Mockito.when(account.getEmailadres()).thenReturn("fakeadres@hva.nl");
//        Mockito.when(accountService.vindAccountOpKlantId(10)).thenReturn(account);
//    }
//
//    @Test
//    void vindTransactieOpTransactieIdHandler() {
//        // geef aan wat de service als teruggeefwaarde heeft
//        Mockito.when(transactieService.vindTransactieOpTransactieId(1)).thenReturn(transactie);
//        // Maak een gesimuleerde request aan
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/transactie/1");
//        // Stuur deze request naar de controller via de MockMvc (try-catch) en ontvang de response
//        try {
//            ResultActions response = mockMvc.perform(request);
//            // Test of de response overeenkomt met de verwachting
//            response.andExpect(MockMvcResultMatchers.status().isOk());
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}