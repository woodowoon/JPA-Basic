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

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            // em.getReference 로 바꿔보자
            // select 가 나가지 않는다.
            Member findMember = em.getReference(Member.class, member.getId());

            // 이건 em.find
            // Member findMember = em.find(Member.class, member.getId()); // 이건 Id를 이용해서 Member 를 가져오고
            // 프록시 가짜 객체라는 뜻이다.
            System.out.println("findMember = " + findMember.getClass()); // findMember = class com.example.ex1hellojpa.oopmapping.domain.Member$HibernateProxy$8yBlReUz
            System.out.println("findMember = " + findMember.getId());
            // getReference 를 호출하는 시점에서는 쿼리가 나가지 않는다. 하지만 실제로 사용할때는 쿼리문이 나가게 된다.
            System.out.println("finfMember.username = " + findMember.getUsername()); // findMember 를 이용해서 getusername 을 가져오게 된다.
            System.out.println("finfMember.username = " + findMember.getUsername()); // 쿼리가 2번 나가지 않는다.ㅍ

            // printMember(member);
            // printMemberAndTeam(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
