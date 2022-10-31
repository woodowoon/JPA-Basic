package com.example.ex1hellojpa.hellojpa;

import javax.persistence.*;

// @Entity
public class Member {
    @Id // 기본키 맵핑
    // 나는 직접 할당보다 값을 생성해서 사용하고 싶어.
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : 숫자의 형태로 차례로 값이 들어가게된다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 시퀀스의 형태로 값이 들어간다. (숫자)
    private Long id;

    @Column(name = "name")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member() {
    }
}
