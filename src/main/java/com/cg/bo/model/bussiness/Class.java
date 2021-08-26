package com.cg.bo.model.bussiness;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;
    private String class_name;
    private int percent_discount;

    public Class(Long class_id) {
        this.class_id = class_id;
    }

    public Class(String class_name) {
        this.class_name = class_name;
    }


}