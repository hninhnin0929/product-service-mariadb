package com.eservice.test.dto;


import com.eservice.test.Entity.Type;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


@Getter
@Setter
public class SearchDto {

    @NotEmpty(message = "Please Insert Product Name!")
    private String name;

    private Type type; /*( Product, Category, HashCode, Review;)*/
}
