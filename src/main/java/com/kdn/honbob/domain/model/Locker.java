package com.kdn.honbob.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locker")
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Locker(String name) {
        this.name = name;
    }
}