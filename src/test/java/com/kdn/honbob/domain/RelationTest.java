package com.kdn.honbob.domain;

import com.kdn.honbob.domain.model.Locker;
import com.kdn.honbob.domain.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RelationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("다대일 관계 테스트")
    @Transactional
    public void 다대일_테스트(){
        System.out.println("===== 다대일 테스트 =====");
        Locker locker = new Locker("1번");
        entityManager.persist(locker);

        Member member = new Member("박기연");
        member.setLocker(locker);
        entityManager.persist(member);
    }
}