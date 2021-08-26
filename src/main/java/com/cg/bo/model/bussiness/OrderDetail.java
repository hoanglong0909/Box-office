package com.cg.bo.model.bussiness;

import com.cg.bo.model.bussiness.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetail_id;
    private String product_name;
    private String product_amount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail(String product_name, String product_amount, double price, Order order) {
        this.product_name = product_name;
        this.product_amount = product_amount;
        this.price = price;
        this.order = order;
    }
}
