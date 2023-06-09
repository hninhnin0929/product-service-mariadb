package com.eservice.test.Entity;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
@Getter
@Setter
public class Review extends AbstractEntity {

    @JsonView(Views.Thin.class)
    private String review;

    @JsonView(Views.Thin.class)
    private String description;

    @JsonView(Views.Thin.class)
    private double rating;

    @JsonView(Views.Thin.class)
    private String date;

    @JsonView(Views.Thin.class)
    private String userName;

    public Review() {
    }

    public Review(String rawData) {
        String[] reviews = rawData.split("\\//");
        if (reviews.length > 1) {
            setReview(reviews[0]);
            setRating(Double.parseDouble(reviews[1]));
            setDate(reviews[2]);
            setUserName(reviews[3].split("on")[0].replace("By", " ").trim());
            setDescription(reviews[4]);
        }
    }
}
