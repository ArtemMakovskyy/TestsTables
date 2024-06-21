package com.winestoreapp.model.antity.state.hibernate.union.subclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("ANOTHER_CHILD")
public class UsChildEntityAnother extends UsBaseEntity {
    private String nameChildHoodAnother;
    private int ageAnother;
}
