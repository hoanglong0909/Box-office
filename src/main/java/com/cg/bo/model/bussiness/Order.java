package com.cg.bo.model.bussiness;

import com.cg.bo.model.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Date order_date;
    private Time order_time;
    private double total_product;
    private double total_ticket;
    private double total_price;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<Ticket> tickets;


    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "orderDetail_id")
    private List<OrderDetail> orderDetails;

//    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Order(Date order_date, Time order_time, double total_product, double total_ticket, double total_price) {
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_product = total_product;
        this.total_ticket = total_ticket;
        this.total_price = total_price;
    }

    public Order(Date order_date, Time order_time, double total_product, double total_ticket, double total_price, Member member) {
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_product = total_product;
        this.total_ticket = total_ticket;
        this.total_price = total_price;
        this.member = member;
    }

    public Order(Date order_date, Time order_time, double total_product, double total_ticket, double total_price, User user, Member member) {
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_product = total_product;
        this.total_ticket = total_ticket;
        this.total_price = total_price;
        this.user = user;
        this.member = member;
    }

    public Order(Date order_date, Time order_time, double total_product, double total_ticket, double total_price, User user) {
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_product = total_product;
        this.total_ticket = total_ticket;
        this.total_price = total_price;
        this.user = user;
    }

    public Order(Long order_id, Date order_date, Time order_time, double total_price, User user) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.user = user;
    }

    public Order(Long order_id, Date order_date, Time order_time, double total_price, User user, Member member) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.user = user;
        this.member = member;
    }
}
