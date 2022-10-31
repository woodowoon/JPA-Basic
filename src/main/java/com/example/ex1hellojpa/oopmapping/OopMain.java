package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Member;
import com.example.ex1hellojpa.oopmapping.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class OopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("bori");
            // 역방향 (주인이 아닌 방향) 만 연관관계 설정 이렇게 할 경우 member team_id null 이 들어간다.
            // team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("dowoon");
            // 이렇게 해줘야 정확히 team_id 에 값이 들어가게 된다.
            member.changeTeam(team); // 이렇게 해줘도 되고
            // team.addMember(member); // 이렇게 해줘도 된다.
            em.persist(member);

            // em.flush();
            // em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for(Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
