//// gemaakt door Anthon van Dijk
//
//package nl.hva.c25.team1.digivault.controller;
//
//import nl.hva.c25.team1.digivault.model.Transactie;
//import nl.hva.c25.team1.digivault.service.TransactieService;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class TransactieControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private List<Transactie> transactieLijst;
//
//    @MockBean
//    private TransactieService transactieService;
//
//    @Autowired
//    public TransactieControllerTest(MockMvc mockMvc) {
//        this.mockMvc = mockMvc;
//    }
//
//    @Test
//    void vindAlleTransactiesOpKoperHandler() {
//        // geef aan wat de service als teruggeefwaarde heeft
//        Mockito.when(transactieService.vindAlleTransactiesOpKoper(10)).thenReturn(transactieLijst);
//        // Maak een gesimuleerde request aan
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/transactie/{koper}", 10);
//        // Stuur deze request naar de controller via de MockMvc (try-catch)
//        try {
//            ResultActions response = mockMvc.perform(request);
//            response.andExpect(MockMvcResultMatchers.status().isOk());
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//
////        3. Ontvang de response.
////        4. Test of de response overeenkomt met de verwachting.
//    }
//}