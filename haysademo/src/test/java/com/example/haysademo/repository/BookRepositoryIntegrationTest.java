package com.example.haysademo.repository;

import com.example.haysademo.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByTitleThenShouldReturnBook() {
        int numberOfLetters = 4;
        Book book = new Book();
        book.setTitle(randomAlphabetic(numberOfLetters));
        book.setAuthor(randomAlphabetic(numberOfLetters));

        testEntityManager.persist(book);

        List<Book> bookFound = bookRepository.findByTitle(book.getTitle());
        assertThat(bookFound.get(0).getTitle()).isEqualTo(book.getTitle());
    }
}
