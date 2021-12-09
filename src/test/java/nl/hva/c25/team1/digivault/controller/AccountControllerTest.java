package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    private static Account annie = new Account(1, "annie@gmail.com", "Annie7890");

    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @Autowired
    public AccountControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

//    @Test
//    void bewaarAccount() {
//    }
//
//    @Test
//    void updateAccount() {
//    }
//
//    @Test
//    void geefAccountOpAccountId() {
////        Mockito.when(this.accountService.vindAccountOpAccountId(1)).thenReturn(annie);
////        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/accounts/1", new Object[0]);
////
////        try {
////            ResultActions response = this.mockMvc.perform(request);
////            response.andExpect(MockMvcResultMatchers.status()
////                    .isOk())
////                    .andExpect(MockMvcResultMatchers.content()
////                    .json(""))
////                    .andDo(MockMvcResultHandlers.print());
////        } catch (Exception var4) {
////            System.out.println("O help, watskbeurt?");
////        }
//    }
//
//    @Test
//    void geefAccountsHandler() {
//    }
}