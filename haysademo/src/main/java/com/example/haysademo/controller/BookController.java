package com.example.haysademo.controller;


import com.example.haysademo.exceptions.BookNotFoundException;
import com.example.haysademo.model.Book;
import com.example.haysademo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Just one note worth pointing out –
 * we're exposing our Book entity as our external resource here.
 * That's fine for our simple application here, but in a real-world application, you will likely want to separate these two concepts.
 * TODO https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 */

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/author/{bookAuthor}")
    public List findByAuthor(@PathVariable String bookAuthor) {
        return bookRepository.findByAuthor(bookAuthor);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable String id) {
        return bookRepository.findById(id)
                .orElseThrow(this::throwBookNotFoundException);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookRepository.findById(id)
                .orElseThrow(this::throwBookNotFoundException);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable String id) {

        bookRepository.findById(id)
                .orElseThrow(this::throwBookNotFoundException);
        return bookRepository.save(book);
    }

    private RuntimeException throwBookNotFoundException() {
        return new BookNotFoundException();
    }

}
