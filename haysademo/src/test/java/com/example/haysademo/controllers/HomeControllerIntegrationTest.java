package com.example.haysademo.controllers;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeControllerIntegrationTest {

    private static final String API_ROOT = "http://localhost:8081/";

    @Test
    public void whenAccessHomePageShouldReturnGreetingsWithAppName() {
        Response response = RestAssured.given().get(API_ROOT);
        String expectedBodyContent = "Welcome to Book API with Spring Boot";

        String actualBodyContent = response.getBody().asString();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertThat(actualBodyContent).contains(expectedBodyContent);
    }

    @Test
    void whenAccessHomePageShouldReturnHomePageTitle() {
        Response response = RestAssured.given().get(API_ROOT);
        String expectedBodyContent = "Book Api Home Page";

        String actualBodyContent = response.getBody().asString();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertThat(actualBodyContent).contains(expectedBodyContent);
    }
}
