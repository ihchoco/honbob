package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;

    private String isbn;

    private String title;

    @ManyToOne
    private BookStore bookStore;

}
