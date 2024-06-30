package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter, Setter 생략
}