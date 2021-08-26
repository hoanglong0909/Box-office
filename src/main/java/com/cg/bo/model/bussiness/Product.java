package com.cg.bo.model.bussiness;

import com.cg.bo.model.bussiness.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(unique = true)
    private String product_name;
    private Long amount;
    private double price;
    private String description;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String product_name, Long amount, double price, String description, Category category) {
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(String product_name, double price, String description, Category category) {
        this.product_name = product_name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(Long product_id, String product_name, Long amount, double price, String description, Category category) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(String product_name, Long amount, double price, String description, boolean isDelete, Category category) {
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.isDelete = isDelete;
        this.category = category;
    }
}
