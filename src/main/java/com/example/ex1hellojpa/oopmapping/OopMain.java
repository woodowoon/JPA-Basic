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

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); // 프록시

            em.detach(refMember);
            // em.close();

            refMember.getUsername(); // 영속성 컨텍스트에 refmember 가 없기 때문에 오류가 뜨게 된다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

}
