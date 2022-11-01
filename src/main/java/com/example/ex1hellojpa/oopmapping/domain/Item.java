package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;

@Entity
// @Inheritance(strategy = InheritanceType.JOINED) // 각각 테이블 따로 생성되는 걸 확인할 수 있다.
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블로 만들어지는걸 확인할 수 있다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Item 테이블이 생성되는 것이 아닌, 각각의 따로 테이블이 만들어지고, ID, Name Price 등 중복으로 들어가게된다. 대신 이건 ITEM 추상클래스로 만들어야된다.
@DiscriminatorColumn // DTYPE 가 생기면서 해당 컬럼에 movie 인지, book 인지 넣어지게 된다.
public class Item {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
