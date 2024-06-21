package com.winestoreapp.model.antity.state.hibernate.union.subclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("CHILD")
public class UsChildEntity extends UsBaseEntity {
    private String nameChildHood;
    private int age;
}
