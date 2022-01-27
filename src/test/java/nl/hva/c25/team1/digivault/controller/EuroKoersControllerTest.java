package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.service.EuroKoersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Testen bij de EuroKoersController
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2021
 */

@WebMvcTest(EuroKoersController.class)
class EuroKoersControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private EuroKoersService euroKoersService;

    @Autowired
    public EuroKoersControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    /**
     * Test van de methode vindEuroKoersOpId uit de EuroKoersController
     */
    @Test
    void vindEuroKoersOpId() {
        EuroKoers euroKoers = new EuroKoers(20, LocalDate.of(2021, 12, 7),
                35.14);

        // EuroKoers als response
        Mockito.when(euroKoersService.vindEuroKoersOpId(20)).thenReturn(euroKoers);
        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.get("/eurokoersen/20");

        // Geen response
        Mockito.when(euroKoersService.vindEuroKoersOpId(1)).thenReturn(null);
        MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders.get("/eurokoersen/1");

        // Error 404: is not found
        MockHttpServletRequestBuilder request3 = MockMvcRequestBuilders.get("/eurokoersen/get/1");
        MockHttpServletRequestBuilder request4 = MockMvcRequestBuilders.get("/eurokoers/");

        // Error 400: bad request
        MockHttpServletRequestBuilder request5 = MockMvcRequestBuilders.get("/eurokoersen/o");

        try {
            ResultActions response = mockMvc.perform(request1);
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"euroKoersId\":20,\"datum\":" +
                            "\"2021-12-07\",\"koers\":35.14,\"assetId\":0}"));

            response = mockMvc.perform(request2);
            response.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$").doesNotExist());

            response = mockMvc.perform(request3);
            response.andExpect(MockMvcResultMatchers.status().isNotFound());

            response = mockMvc.perform(request4);
            response.andExpect(MockMvcResultMatchers.status().isNotFound());

            response = mockMvc.perform(request5);
            response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
