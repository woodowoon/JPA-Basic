package com.example.ex1hellojpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin(); // 트랜잭션 시작

        try {
            // 영속
            // 첫번째 findMember1 에서는 DB에서 조회해오지만 2번째 findMember2 에서는 1차 캐시에서 조회해오기때문에 쿼리문이 날라가지않는다.
            Member findMember1 = em.find(Member.class, 1L); // select 쿼리O
            Member findMember2 = em.find(Member.class, 1L); // select 쿼리X

            // 영속 엔티티의 동일성을 보장한다.
            System.out.println("result = " + (findMember1 == findMember2)); // true

            tx.commit(); // 트랜잭션 커밋 -> 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
