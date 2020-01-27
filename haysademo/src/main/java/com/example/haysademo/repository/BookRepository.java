package com.example.haysademo.repository;

import com.example.haysademo.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);
}