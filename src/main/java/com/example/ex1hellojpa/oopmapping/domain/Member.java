package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;

// 연관관계 매핑 기초 - 단방향 연관관계
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne // member 입장에서는 many 이고 팀 입장에서는 one 이기때문에 ManyToOne 를 사용한다. / 관계가 뭔지?
    @JoinColumn(name = "TEAM_ID") // 조인할때 관계가 있는 컬럼이 뭔대?
    private Team team;

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

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
