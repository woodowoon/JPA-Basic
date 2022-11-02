package com.example.ex1hellojpa.oopmapping;

import com.example.ex1hellojpa.oopmapping.domain.Address;
import com.example.ex1hellojpa.oopmapping.domain.Member;
import com.example.ex1hellojpa.oopmapping.domain.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /**
         * - 엔티티타입
         *      - 데이터가 변해도 식별자로 지속해서 추적 가능하다.
         *      - @Entity 로 정의하는 객체
         * - 값타입
         *      - 식별자가 없고 값만 있으므로, 변경시 추적 불가능하다.
         *      - int, Integer, String 처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체
         *      - 생명주기를 엔티티에 의존한다.
         *      - ex) 회원을 삭제하는 순간 이름과 나이 필드도 함께 삭제된다.
         *      - 값 타입은 공유하면 안된다.
         *      - ex) 회원 이름 변경시 다른 회원의 이름도 함께 변경되면 안된다.
         * - 임베디드 타입(embedded type, 복합 값 타입)
         *      - int, string 같은 타입이다.
         *      - ex) 회원 엔티티는 이름, 근무시작일, 근무종료일, 주소도시, 주소번지, 주소우편번호를 갖는다.
         *          - 회원 엔티티는 이름, 근무기간, 집주소를 가진다. 라고 설명할 수 있다.
         *          - @Embeddable : 값 타입을 정의하는 곳에 표시
         *          - @Embedded : 값 타입을 사용하는 곳에 표시
         *      - 장점 : 높은 재사용, 높은 응집도, 해당 값 타입만 사용하는 의미있는 메소드를 만들 수 있다., 엔티티 생명주기에 의존한다.
         * - 컬렉션 값 타입(collection value type)
         */
        try {
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeaddress(new Address("city", "street", "10")); // member 을 통해서 값을 넣을 수 있다.
            member.setWorkPeriod(new Period());

            em.persist(member);

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
