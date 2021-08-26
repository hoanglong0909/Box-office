package com.cg.bo.model.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "typeseats")
public class TypeSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long type_id;
    private String type_name;
    private double price;

    public TypeSeat(String type_name, double price) {
        this.type_name = type_name;
        this.price = price;
    }
}
