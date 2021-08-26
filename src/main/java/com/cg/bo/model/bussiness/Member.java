package com.cg.bo.model.bussiness;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    private String member_name;
    private String phoneNumber;
    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "visit_id")
    private List<Visit> visits;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Order> orders;

    @ManyToOne(targetEntity = Class.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Class aClass;

//    public Member(String member_name, String phoneNumber, String email, List<Visit> visits, List<Order> orders, Class aClass) {
//        this.member_name = member_name;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.visits = visits;
//        this.orders = orders;
//        this.aClass = aClass;
//    }

    public Member(Long member_id, String member_name, String phoneNumber, String email) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Member(String member_name, String phoneNumber, String email, Class aClass) {
        this.member_name = member_name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aClass = aClass;
    }

    public Member(String member_name, String phoneNumber, String email) {
        this.member_name = member_name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


//    public Member(String member_name, String phoneNumber, String email, List<Visit> visits, List<Order> orders) {
//        this.member_name = member_name;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.visits = new ArrayList<>();
//        this.orders = new ArrayList<>();
//    }

    public Member(Long member_id) {
        this.member_id = member_id;
    }
}
