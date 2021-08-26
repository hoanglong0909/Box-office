package com.cg.bo.repository;


import com.cg.bo.model.projection.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film,Long> {

    @Query("select f from Film f where f.film_id = :id")
    Optional<Film> findByIdFilm(Long id);

    @Query("select f from Film f where f.isDelete = FALSE order by f.film_id asc ")
    Iterable<Film> findAllByOrderByFilm_idDesc();

    @Query("select f from Film f where f.status = TRUE")
    Iterable<Film> findAllByStatusTrue();

}