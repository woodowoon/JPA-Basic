package com.example.ex1hellojpa.jpabook.jpashop;

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

            // 양방향 연관관계가 아니여도 애플리케이션을 개발하는데 큰 문제가 없다.
            // 하지만 양방향 연관관계를 만드는 이유는 개발성의 편의를 위해서 만들게 된다.
            // 핵심은 최대한 단방향으로 하지만, 조회를 편하게 하기 위해서는 양방향으로 해야될일이 많이 생기게 된다.
            Order order = new Order();
            em.persist(order);
            // order.addOrderItem(new OrderItem());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
