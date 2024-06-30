package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class BookStore {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bookStore")
    private List<Book> books = new ArrayList<>();

    public void add(Book book){
        this.books.add(book);
    }
}
