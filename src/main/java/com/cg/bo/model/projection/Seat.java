package com.cg.bo.model.projection;

import com.cg.bo.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seat_id;
    private String seat_name;

    @OneToOne
    @JoinColumn(name = "status_id")
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name= "type_id")
    private TypeSeat typeSeat;

    @OneToOne
    @JoinColumn(name = "user_id", columnDefinition = "bigint default 1")
    private User user;

    public Seat(String seat_name, SeatStatus seatStatus, Room room, TypeSeat typeSeat) {
        this.seat_name = seat_name;
        this.seatStatus = seatStatus;
        this.room = room;
        this.typeSeat = typeSeat;
    }
}