package com.cg.bo.model.projection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;
    private String room_name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @Column(name = "isFull", columnDefinition = "boolean default false")
    private boolean isFull;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "seat_id")
    private List<Seat> seats;
}
