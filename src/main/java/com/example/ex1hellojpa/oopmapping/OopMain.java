package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Movie;

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

        try {

            Movie movie = new Movie();
            movie.setDirector("감독1");
            movie.setActor("배우1");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);
            movie.setCreateBy("dowoon");
            movie.setCreatedDate(LocalDateTime.now()); // 분명 item 에 있는 속성인데 extend 를 통해서 movie 에서도 사용할 수 있다.

            em.persist(movie);

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
