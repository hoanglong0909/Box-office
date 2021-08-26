package com.cg.bo.model.bussiness;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String category_name;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    private List<Product> products;

    public Category(Long category_id) {
        this.category_id = category_id;
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }

    public Category(Long category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Category(String category_name, List<Product> products) {
        this.category_name = category_name;
        this.products = products;
    }
}
