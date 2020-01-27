package com.example.haysademo.controllers;

import com.example.haysademo.model.Book;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerIntegrationTest {

    private static final String API_ROOT = "http://localhost:8081/api/books";

    private Book createBook() {
        int numberOfLetters = 10;
        Book book = new Book();
        book.setTitle(randomAlphabetic(numberOfLetters));
        book.setAuthor(randomAlphabetic(numberOfLetters));
        return book;
    }

    private String createBookAsUri(Book book) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }

    @Test
    public void whenGetAllBooksThenShouldReturnOk() {
        Response response = RestAssured.given().get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetAllBooksByTitleShouldReturnOk() {
        Book book = createBook();
        createBookAsUri(book);
        Response response = RestAssured.get(
                API_ROOT + "/title/" + book.getTitle());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }

    @Test
    void whenGetCreatedBookByAuthorShouldReturnOk() {

        Book book = createBook();
        createBookAsUri(book);

        Response response = RestAssured.get(API_ROOT + "/author/" + book.getAuthor());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.getBody().asString().contains(book.getAuthor()));

    }

    @Test
    public void whenGetCreatedBookByIdShouldReturnOk() {
        Book book = createBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(book.getTitle(), response.jsonPath().get("title"));
    }

    @Test
    public void whenGetNotExistsBookByIdThenShouldReturnNotFound() {
        int quantityOfRandomNumbers = 4;
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(quantityOfRandomNumbers));

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
        assertEquals("Book not found", response.getBody().asString());
    }

    @Test
    public void whenCreateNewBookShouldReturnCreated() {
        Book book = createBook();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidBookThenShouldReturnError() {
        Book book = createBook();
        book.setAuthor(null);
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedBookThenShouldUpdate() {
        Book book = createBook();
        String location = createBookAsUri(book);

        book.setId(Long.parseLong(location.split("api/books/")[1]));
        book.setAuthor("newAuthor");
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .put(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath()
                .get("author"));
    }

    @Test
    void whenUpdateCreatedBookWithWrongIdThenShouldReturnMismatchError() {
        Book book = createBook();
        String location = createBookAsUri(book);

        book.setId(4);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .put(location);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
        assertThat(response.asString()).contains("Book ID Mismatch");

    }

    @Test
    public void whenDeleteCreatedBookThenShouldReturnOk() {
        Book book = createBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.delete(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
}
