package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// @Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;


    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    // @ElementCollection
    // @CollectionTable(name = "ADDRESS", joinColumns =
    //     @JoinColumn(name = "MEMBER_ID")
    // )
    // private List<Address> addressesHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // 값 타입보다 훨씬 더 좋다.
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressesHistory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

//    public List<Address> getAddressesHistory() {
//        return addressesHistory;
//    }
//
//    public void setAddressesHistory(List<Address> addressesHistory) {
//        this.addressesHistory = addressesHistory;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public List<AddressEntity> getAddressesHistory() {
        return addressesHistory;
    }

    public void setAddressesHistory(List<AddressEntity> addressesHistory) {
        this.addressesHistory = addressesHistory;
    }

}
