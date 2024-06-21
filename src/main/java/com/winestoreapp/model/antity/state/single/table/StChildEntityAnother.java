package com.winestoreapp.model.antity.state.single.table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("ANOTHER_CHILD")
public class StChildEntityAnother extends StBaseEntity {
    private String nameChildHoodAnother;
    private int ageAnother;
}
