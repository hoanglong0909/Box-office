package com.cg.bo.model.projection;

import com.cg.bo.model.bussiness.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long show_id;
    private Time time_start;
    private Time time_end;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonIgnore
    @OneToMany(targetEntity = Ticket.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    private List<Ticket> tickets;

    @Column(name = "status",columnDefinition = "boolean default true")
    private boolean status;

    public Show(Time time_start, Time time_end, Film film, Schedule schedule, Room room) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.film = film;
        this.schedule = schedule;
        this.room = room;
    }

    public Show(Time time_start, Time time_end, Film film, Schedule schedule, Room room, boolean status) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.film = film;
        this.schedule = schedule;
        this.room = room;
        this.status = status;
    }
}