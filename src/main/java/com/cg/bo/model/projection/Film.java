package com.cg.bo.model.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long film_id;
    private String film_name;
    private String image;
    private String duration;
    private Date rel_date;
    private Date exp_date;

    @Column(name = "admit", columnDefinition = "bigint default 0")
    private Long admit;
    private String description;


    @Column(columnDefinition = "boolean default false")
    private boolean isDelete;

    @Column(columnDefinition = "boolean default true")
    private boolean status;

    public Film(Long film_id) {
        this.film_id = film_id;
    }

    public Film(String film_name, String image, String duration, Date rel_date, Date exp_date, Long admit, String description) {
        this.image = image;
        this.film_name = film_name;
        this.duration = duration;
        this.rel_date = rel_date;
        this.exp_date = exp_date;
        this.admit = admit;
        this.description = description;
    }

    public Film(Long film_id, String film_name, String image, String duration, Date rel_date, Date exp_date, String description) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.image = image;
        this.duration = duration;
        this.rel_date = rel_date;
        this.exp_date = exp_date;
        this.description = description;
    }
}