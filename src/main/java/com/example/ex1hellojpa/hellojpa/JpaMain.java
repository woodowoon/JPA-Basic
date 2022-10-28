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
            Member member = em.find(Member.class, 100L);
            member.setName("AAAA");

            // 준영속상태 디태치
            em.detach(member); // 더이상 JPA에서 관리하지않는다. 쿼리문 실행 XX

            em.clear(); // 모든걸 초기화, 준영속상태로 변경, 디태치 상태

            em.close(); // 영속성 컨텍스트를 종료

            tx.commit(); // 트랜잭션 커밋 -> 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
