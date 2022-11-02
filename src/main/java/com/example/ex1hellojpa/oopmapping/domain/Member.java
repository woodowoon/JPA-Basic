package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;
import java.util.List;

// 연관관계 매핑 기초 - 단방향 연관관계
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // @ManyToOne(fetch = FetchType.LAZY) // Lazy : 지연로딩 - 멤버만 조회하고 팀은 거의 사용하지 않을때,
    @ManyToOne(fetch = FetchType.EAGER) // EAGER : 즉시로딩 - 멤버와 팀을 함께 조회한다.
    @JoinColumn
    private Team team;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
