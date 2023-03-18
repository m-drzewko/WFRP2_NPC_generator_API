package com.drzewek.wfrp_npc_generator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Random;

@Configuration
public class BeanConfig {

    @Bean
    public Random random() {
        return new Random();
    }
}
