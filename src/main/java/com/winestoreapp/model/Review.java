package com.winestoreapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private User user;
    private Wine wine;
    private WineRating rating;
    private String review;
}
