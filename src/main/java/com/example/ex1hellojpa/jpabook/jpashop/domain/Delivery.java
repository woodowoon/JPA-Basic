package com.example.ex1hellojpa.jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

// @Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long Id;

    private String city;
    private String street;
    private String zipcode;
    private DeliverStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

}
