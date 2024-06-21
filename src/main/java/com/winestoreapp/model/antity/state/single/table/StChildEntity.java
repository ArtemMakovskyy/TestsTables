package com.winestoreapp.model.antity.state.single.table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("CHILD")
public class StChildEntity extends StBaseEntity {
    private String nameChildHood;
    private int age;
}
