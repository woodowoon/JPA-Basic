package com.example.ex1hellojpa.hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // JPA를 사용하는 애구나 하고 알게된다. 애노테이션
// @Table(name = "User") 테이블 이름이 다를 경우 이런식으로 매핑할 수 있다.
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입을 사용할 수 있다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate; // 년 월
    private LocalDateTime testLocalDateTime; // 년 월 일

    @Lob
    private String description;

    @Transient // 난 DB에 얠 넣고싶지않아 신경쓰고싶지않아, 할때 쓰는것.
    private int temp;

    public Member() {
    }
}
