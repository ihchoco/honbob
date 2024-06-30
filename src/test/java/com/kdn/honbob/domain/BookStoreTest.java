package com.kdn.honbob.domain;

import com.kdn.honbob.domain.model.Book;
import com.kdn.honbob.domain.model.BookStore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookStoreTest {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    @Test
    @DisplayName("BookStore 테스트")
    @Transactional
    public void 북스토어_테스트() {
        BookStore bookStore = new BookStore();
        bookStore.setName("교보문고");
        entityManager.persist(bookStore);

        Book book = new Book();
        book.setTitle("JPA 가이드");

        bookStore.add(book);
        entityManager.persist(book);

        entityManager.flush();
    }
}
