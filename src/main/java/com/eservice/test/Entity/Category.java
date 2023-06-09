package com.eservice.test.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
public class Category extends AbstractEntity {

    @JsonView(Views.Thin.class)
    private String name;

    @JsonView(Views.Thin.class)
    private String website;

    @JsonView(Views.Thin.class)
    private String companyName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Category_Product", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> productList = new ArrayList<Product>();

    public Category(){}

    public Category(String name){
        setName(name);
    }
}
