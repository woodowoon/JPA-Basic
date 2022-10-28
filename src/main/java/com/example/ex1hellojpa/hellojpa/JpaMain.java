package com.example.ex1hellojpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin(); // 트랜잭션 시작

        try {
            // 영속
            Member member = new Member();
            member.setId(200L);
            member.setName("member");
            em.persist(member);

            em.flush(); // 플러시. 이시점에 데이터베이스 쿼리가 바로 날라가게 된다.

            tx.commit(); // 트랜잭션 커밋 -> 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
