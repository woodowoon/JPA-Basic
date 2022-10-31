package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // 양방향 연관관계
    @OneToMany(mappedBy = "team") // mappedBy: 이건 주인이 아니다, 그러니 읽기만 가능 / 나는 team 에 의해 관리되는 거야.
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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

    public void setName(String username) {
        this.name = username;
    }

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
}
