package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.service.AssetService;
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

/**
 * Testen van de EuroKoersController
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2021
 */

@WebMvcTest(EuroKoersControllerTest.class)
class EuroKoersControllerTest {
//
//    private final MockMvc mockMvc;
//
//    @MockBean
//    private EuroKoersService euroKoersService;
//
//    @Autowired
//    public EuroKoersControllerTest(MockMvc mockMvc) {
//        this.mockMvc = mockMvc;
//    }
//
//    @Test
//    void vindEuroKoersOpId() {
//        EuroKoers euroKoers = new EuroKoers(1, LocalDate.of(2022, 01, 01),
//                41703.4252);
//
//        Mockito.when(euroKoersService.vindEuroKoersOpId(1)).thenReturn(euroKoers);
//        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.get("/eurokoersen/1");
//
//        try {
//            ResultActions response = mockMvc.perform(request1);
//            response.andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.content().json("{\"euroKoersId\":1,\"datum\":" +
//                            "\"2022-01-01\",\"koers\":41703.4252,\"assetId\":0}"));
//
//        }
//        catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//    }
}
