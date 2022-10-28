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

        try{
            // code
            // 영속 상태는 무엇이냐?
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA"); // 여기까지 비영속 상태이다.

            // 영속 상태로 변한다.
            System.out.println("BEFORE");
            em.persist(member); // 이때 DB에 저장되는 것이 아니라 commit 해야 DB에 저장된다.
            em.detach(member); // 영속성 컨텍스트에서 해당 내용을 지운다.
            System.out.println("AFTER"); // Before After 이 전부 실행된 후에 insert 쿼리가 날라간다.

            tx.commit(); // 트랜잭션 커밋 -> 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
