package com.drzewek.wfrp_npc_generator;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WfrpNpcGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfrpNpcGeneratorApplication.class, args);
	}

}
