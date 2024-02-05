package org.sber.resourcesservice.controller;

import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sber.bookingentity.entity.Hotel;
import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.sber.resourcesservice.repository.ReservationPeriodRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationControllerTest {

    private final ReservationPeriodRepository periodRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:15-alpine"
    );

    @BeforeAll
    static void beforeAll(){
        postgres.start();
    }

    @AfterAll
    static void afterAll(){
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp(){
        RestAssured.baseURI = "http://localhost:" + port;
        periodRepository.deleteAll();
    }

    @Test
    void shouldGetAllReservations(){
        Resource resource = new Hotel(5, 10);
        periodRepository.saveAndFlush(resource);
        List<ReservationPeriod> reservations = List.of(
                new ReservationPeriod(1L, 1L, 1L, )
        )
    }
}