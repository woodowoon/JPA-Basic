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
            // 변경 감지
            // 엔티티 수정은 변경감지에 의해서 하게된다.
            Member member = em.find(Member.class, 100L);
            member.setName("ㅋㅋㅋ"); // em.persist 를 해주지 않아도 변경된다. -> 자바컬렉션을 이용하는 것 처럼 이용하면 되는 부분이다.

            // 삭제
            Member memberA = em.find(Member.class, 100L);
            em.remove(memberA);

            tx.commit(); // 트랜잭션 커밋 -> 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
