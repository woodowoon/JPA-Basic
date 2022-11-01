package com.example.ex1hellojpa.jpabook.jpashop;

import com.example.ex1hellojpa.jpabook.jpashop.domain.Book;
import com.example.ex1hellojpa.jpabook.jpashop.domain.Order;
import com.example.ex1hellojpa.jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("1달러 경제여행");
            book.setAuthor("우도운");

            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
