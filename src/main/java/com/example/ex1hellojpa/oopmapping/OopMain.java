package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Member;
import com.example.ex1hellojpa.oopmapping.domain.Movie;
import com.example.ex1hellojpa.oopmapping.domain.Team;
import org.hibernate.Hibernate;

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
         * 프록시와 지연로딩
         *  - 실무에서는 가급적 지연로딩만을 사용한다.
         *      - 예상치 못한 쿼리가 나가게 된다.
         *      - 즉시로딩을 사용하게 되면 JPQL에서 N+1 문제를 일으킨다. | N+1 문제 : 쿼리를 1개 날렸는데 추가 N개의 쿼리가 나간다고 해서 N+1 문제라고 한다.
         *      - Lazy 로 설정해야한다.
         */
        try {
            // member 을 조회할때 team 도 함께 가져와야 할까?

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Team team = new Team();
            team.setName("member1");
            em.persist(team);

            member1.setTeam(team);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());

            System.out.println("m = " + m.getTeam().getClass()); // 프록시

            System.out.println("===============");
            // 프록시로 가져와서 실제로 사용했을때, 쿼리를 생성해주게 된다. : LAZY
            // EAGER 를 사용하게 되면 초기화가 필요가 없다.
            m.getTeam().getName();
            System.out.println("===============");

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
