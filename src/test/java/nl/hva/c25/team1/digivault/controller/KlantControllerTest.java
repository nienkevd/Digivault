package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.LoginService;
import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testen bij KlantController
 *
 * @author Anneke, studentnummer 500889251
 */

@WebMvcTest(KlantController.class)
class KlantControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private KlantService klantService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AssetService assetService;

    @MockBean
    private EuroKoersService euroKoersService;

    @MockBean
    private FinancieelOverzichtService financieelOverzichtService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PortefeuilleItemService portefeuilleItemService;

    @MockBean
    private RegistratieService registratieService;

    @MockBean
    private RekeningService rekeningService;

    @MockBean
    private TransactieService transactieService;

    @Autowired
    public KlantControllerTest(MockMvc mockMvc) {
        super();
        this.mockMvc = mockMvc;
    }

    @Test
    void vindKlantOpEmailadresHandler() {
        Klant marieke = new Klant(10,"080772547", LocalDate.parse("1983-07-05"));
        Mockito.when(klantService.vindKlantOpEmail("marieke@gmail.com")).thenReturn(marieke);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/klant/marieke@gmail.com");

        try {
            ResultActions response = mockMvc.perform(request);
            response
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers
                            .content()
                            .json("{    \"transactiepartijId\": 10,\n" +
                            "    \"rekening\": null,\n" +
                            "    \"portefeuille\": null,\n" +
                            "    \"bsn\": \"080772547\",\n" +
                            "    \"geboortedatum\": \"1983-07-05\",\n" +
                            "    \"naam\": null,\n" +
                            "    \"adres\": null,\n" +
                            "    \"account\": null}"))
                    .andDo(MockMvcResultHandlers.print());

        } catch (Exception e) {
            System.out.println("O help, watskbeurt?");
        }
    }

    @Test
    void vindKlantopKlantIdHandler() {
        Klant marieke = new Klant(10,"080772547", LocalDate.parse("1983-07-05"));
        Mockito.when(klantService.vindKlantOpKlantID(10)).thenReturn(marieke);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/klanten/10");

        try {
            ResultActions response = mockMvc.perform(request);
            response
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers
                            .content()
                            .json("{    \"transactiepartijId\": 10,\n" +
                                    "    \"rekening\": null,\n" +
                                    "    \"portefeuille\": null,\n" +
                                    "    \"bsn\": \"080772547\",\n" +
                                    "    \"geboortedatum\": \"1983-07-05\",\n" +
                                    "    \"naam\": null,\n" +
                                    "    \"adres\": null,\n" +
                                    "    \"account\": null}"))
                    .andDo(MockMvcResultHandlers.print());

        } catch (Exception e) {
            System.out.println("O help, watskbeurt?");
        }
    }
}