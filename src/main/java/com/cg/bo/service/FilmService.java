package com.cg.bo.service;

import com.cg.bo.model.projection.Film;

public interface FilmService extends GeneralService<Film> {
    Iterable<Film> findAllByOrderByFilm_idDesc();

    Iterable<Film> findAllByStatusTrue();

    void checkAvailable(Iterable<Film> films);
}