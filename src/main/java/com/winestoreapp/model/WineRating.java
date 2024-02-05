package com.winestoreapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@Entity
//@Table(name = "wine_ratings")
//@ToString
public class WineRating {
//    @Id
    private Long wine_id;

    private Integer rating;
}
