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
         *  - 실무에서는 값타입컬렉션보다 일대다를 고려하는 것이 좋다.
         */
        try {

            Member member = new Member();
            member.setUsername("member");
            member.setHomeAddress(new Address("homecity", "street", "1000"));

            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("소고기");

            // member.getAddressesHistory().add(new Address("old1", "street", "1000"));
            // member.getAddressesHistory().add(new Address("old2", "street", "1000"));

            // 위의 방법이 아닌 다른 방법


            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START =================");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("===============  END =================");

            // homeCity -> newCity
            // findMember.getHomeAddress().setCity("new City"); // 이것은 안된다.
            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("new City", oldAddress.getStreet(), oldAddress.getZipcode())); // 값타입을 통으로 갈아끼어야한다.

            // 피자 -> 한식
            findMember.getFavoriteFoods().remove("피자");
            findMember.getFavoriteFoods().add("한식");

            System.out.println("=============== START =================");

            // findMember.getAddressesHistory().remove(new Address("old1", "street", "1000")); // 그냥 모든걸 삭제하고
            // findMember.getAddressesHistory().add(new Address("new City1", "street", "1000")); // insert 한다.

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
