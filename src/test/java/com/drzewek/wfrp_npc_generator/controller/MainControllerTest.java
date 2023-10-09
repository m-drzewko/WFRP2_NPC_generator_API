package com.drzewek.wfrp_npc_generator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void shouldPrintMessage() throws Exception {
//        //given
//        String expectedString = "Random NPC generator for WFRP II ed";
//
//        //when
//        MvcResult mvcResult =
//                mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();
//        String responseMessage = mvcResult.getResponse().getContentAsString();
//
//        //then
//        assertEquals(expectedString, responseMessage);
//    }
}