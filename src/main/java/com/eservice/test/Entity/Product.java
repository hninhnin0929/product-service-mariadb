package com.eservice.test.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product extends AbstractEntity {

    @JsonView(Views.Thin.class)
    private String uniqueId;

    @JsonView(Views.Thin.class)
    private String name;

    @JsonView(Views.Thin.class)
    private String sellingPrice;

    @JsonView(Views.Thin.class)
    private String manufacturer;

    @JsonView(Views.Thin.class)
    private String noOfAvailableStock;

    @JsonView(Views.Thin.class)
    private String modelNumber;

    @JsonView(Views.Thin.class)
    private String dimension;

    @JsonView(Views.Thin.class)
    private String shippingWeight;

    @JsonView(Views.Thin.class)
    private String rating;

    @JsonView(Views.Thin.class)
    private String image;

    @JsonView(Views.Thin.class)
    private String productUrl;

    @JsonView(Views.Thin.class)
    private String description;

    @JsonView(Views.Thin.class)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hashCode")
    private HashCode hashcode;

    @JsonView(Views.Thin.class)
    @ManyToMany(mappedBy = "productList")
    private List<Category> categoryList = new ArrayList<Category>();

    @JsonView(Views.Thin.class)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Product_Review", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "reviewId"))
    private List<Review> reviewList = new ArrayList<Review>();

    public Product() {
    }

    public Product(Row row) {
        setUniqueId(row.getCell(0) + "");
        setName(row.getCell(1) + "");
        setSellingPrice(row.getCell(2) + "");
        setManufacturer(row.getCell(3) + "");
        setNoOfAvailableStock(row.getCell(4) + "");
        setModelNumber(row.getCell(5) == null ? "" : row.getCell(5) + "");
        setDimension(row.getCell(6) == null ? "" : row.getCell(6) + "");
        setShippingWeight(row.getCell(7) == null ? "" : row.getCell(7) + "");

        setRating(row.getCell(8) == null? "" : row.getCell(7) + "");
        setImage(row.getCell(9) == null? "" : row.getCell(9) + "");

        setProductUrl(row.getCell(10) + "");
        setDescription(row.getCell(11) == null ? "" : row.getCell(11) + "");
    }
}
