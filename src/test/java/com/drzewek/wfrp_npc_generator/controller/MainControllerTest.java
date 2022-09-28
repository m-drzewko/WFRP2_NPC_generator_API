package com.drzewek.wfrp_npc_generator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MainController mainController = new MainController();

    @Test
    public void shouldPrintMessage() throws Exception{
        //given
        String expectedString = "Random NPC generator for WFRP II ed";

        //when

        //then
        assertThat(this.mainController.index().getStatusCode().is2xxSuccessful());
        assertThat(this.mainController.index().getBody().equals(expectedString));


    }
}