package com.drzewek.wfrp_npc_generator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest
class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Test
    void shouldPrintMessage() {
        //given
        String expectedString = "Random NPC generator for WFRP II ed";

        //when
        //then
        assertThat(mainController.index().getStatusCode().is2xxSuccessful());
        assertThat(mainController.index().getBody().equals(expectedString));
    }
}