package com.example.ex1hellojpa.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA를 사용하는 애구나 하고 알게된다. 애노테이션
// @Table(name = "User") 테이블 이름이 다를 경우 이런식으로 매핑할 수 있다.
public class Member {

    @Id
    private Long id;
    // @Column(name = "username") 컬럼명도 다를 경우 이런식으로 매핑이 가능하다.
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
