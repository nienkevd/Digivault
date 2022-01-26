package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.service.RekeningService;
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

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(RekeningController.class)
class RekeningControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private RekeningService rekeningService;

    @Autowired
    public RekeningControllerTest(MockMvc mockMvc) {
        super();
        this.mockMvc = mockMvc;
    }


    @Test
    //checkt of de rekening in het goede format word word doorgegeven.
    void vindRekeningOpIBAN() {
            Rekening expected = new Rekening(1,"AA00ABCD0000000000", 500);
            Mockito.when(rekeningService.vindRekeningOpId(1)).thenReturn(expected);

            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rekeningen/AA00ABCD0000000000");

            try{
                ResultActions response = mockMvc.perform(request);
                response
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers
                                .content()
                                .json("{    \"rekeningId\": 1,\n" + "\"IBAN\": AA00ABCD0000000000"
                                        + "\"saldo\": 500" ))
                        .andDo(MockMvcResultHandlers.print());

            }catch (Exception e) {
                System.out.println("Er gaat iets fout");
            }
    }

    @Test
    void vindRekeningOpID() {
        Rekening expected = new Rekening(1,"AA00ABCD0000000000", 500);
        Mockito.when(rekeningService.vindRekeningOpId(1)).thenReturn(expected);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/rekeningen/1");

        try{
            ResultActions response = mockMvc.perform(request);
            response
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers
                           .content()
                            .json("{    \"rekeningId\": 1,\n" + "\"IBAN\": AA00ABCD0000000000"
                                    + "\"saldo\": 500" ))
                    .andDo(MockMvcResultHandlers.print());

        }catch (Exception e) {
            System.out.println("Er gaat iets fout");
        }



    }

}