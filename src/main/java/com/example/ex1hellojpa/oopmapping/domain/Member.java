package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 집 주소
    @Embedded
    private Address Homeaddress;

    // 회사 주소
    @Embedded
    @AttributeOverrides({ // Address 를 둘다 사용하고 싶은 경우에 이런식으로 사용할 수 있다.
            @AttributeOverride(name="city",
            column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
            column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
            column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address Workaddress;


    // 기간 Period
    @Embedded
    private Period workPeriod;

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

    public Address getHomeaddress() {
        return Homeaddress;
    }

    public void setHomeaddress(Address homeaddress) {
        Homeaddress = homeaddress;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }
}
