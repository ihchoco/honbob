package com.kdn.honbob.presentation.controller;

import com.kdn.honbob.domain.model.Locker;
import com.kdn.honbob.domain.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    @PersistenceContext
    private final EntityManager entityManager;

    @GetMapping("/test")
    @Transactional
    public void test(){
        Locker locker = new Locker("1번");
        entityManager.persist(locker);


        Member member = new Member("박기연");
        member.setLocker(locker);
        entityManager.persist(member);


    }
}
