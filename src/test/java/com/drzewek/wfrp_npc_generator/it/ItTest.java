package com.drzewek.wfrp_npc_generator.it;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class ItTest {

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(5432)
            .withInitScript("init.sql");

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterEach
    void tearDown() {
        container.stop();
    }

    @Test
    void test() {
        System.out.println("test");
    }
}
