package com.example.ex1hellojpa.oopmapping.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// @Entity
@DiscriminatorValue("M") // DTYPE 에 들어갈 값을 세팅해준다. 기본값은 Entity 값이랑 같다.
public class Movie extends Item {
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
