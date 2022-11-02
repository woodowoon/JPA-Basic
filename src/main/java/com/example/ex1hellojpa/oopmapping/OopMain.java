package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Address;
import com.example.ex1hellojpa.oopmapping.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class OopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /**
         * - 값 타입의 비교
         *  - 값 타입: 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야한다.
         *
         * - 값 타입 컬렉션
         *  - 값타입을 컬렉션에 담아서 쓰는것.
         */
        try {

            Member member = new Member();
            member.setUsername("member");
            member.setHomeAddress(new Address("homecity", "street", "1000"));

            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("소고기");

            member.getAddressesHistory().add(new Address("old1", "street", "1000"));
            member.getAddressesHistory().add(new Address("old2", "street", "1000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START =================");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("===============  END =================");

            // 컬렉션은 Lazy : 지연로딩이라는 뜻이다.
            List<Address> addressHistory = findMember.getAddressesHistory();
            for(Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

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
