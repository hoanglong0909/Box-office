package com.cg.bo.model.bussiness;

import com.cg.bo.model.projection.Seat;
import com.cg.bo.model.security.User;
import com.cg.bo.model.projection.Show;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
    private double price;

    @OneToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public Ticket(double price, Show show, Seat seat, Order order) {
        this.price = price;
        this.show = show;
        this.seat = seat;
        this.order = order;
    }
}