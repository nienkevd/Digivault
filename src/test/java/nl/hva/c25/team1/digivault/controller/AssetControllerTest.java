package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.service.AssetService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Testen van de AssetController
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2021
 */

@WebMvcTest(AssetController.class)
class AssetControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private AssetService assetService;

    @Autowired
    public AssetControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void vindAssetOpId() {
        Asset asset = new Asset(8, "DOGE", "Dogecoin");

        // Asset als response
        Mockito.when(assetService.vindAssetOpId(8)).thenReturn(asset);
        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.get("/assets/8");

        // Geen response
        Mockito.when(assetService.vindAssetOpId(1)).thenReturn(null);
        MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders.get("/assets/1");

        // Error 404: is not found
        MockHttpServletRequestBuilder request3 = MockMvcRequestBuilders.get("/assets/get/1");
        MockHttpServletRequestBuilder request4 = MockMvcRequestBuilders.get("/asset/");

        // Error 400: bad request
        MockHttpServletRequestBuilder request5 = MockMvcRequestBuilders.get("/assets/o");


        try {
            ResultActions response = mockMvc.perform(request1);
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"assetId\":8,\"afkorting\":\"DOGE\"," +
                            "\"naam\":\"Dogecoin\",\"dagKoers\":0.0}"));

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
