package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Member;
import com.example.ex1hellojpa.oopmapping.domain.Movie;
import com.example.ex1hellojpa.oopmapping.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class OopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /**
         * 프록시 기초
         *  em.find(): 데이터베이스를 통해서 실제 엔티티 객체 조회
         *  em.getReference(): 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
         */
        try {
            // member 을 조회할때 team 도 함께 가져와야 할까?

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass()); // Type : Member 프록시가 아니고 진짜가 나왔다.

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass()); // 이것도 프록시가 아니고 진짜가 나온다..

            // 한 영속성 컨텍스트 안에서 가져오기 때문에 항상 true 여야만 한다.
            System.out.println("a == a : " + (m1 == reference) ); // true

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
