package com.cg.bo.controller;

import com.cg.bo.model.projection.Film;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.service.impl.FilmServiceImpl;
import com.cg.bo.service.impl.ScheduleServiceImpl;
import com.cg.bo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmServiceImpl filmService;

    @Autowired
    protected ScheduleServiceImpl scheduleService;

    @Autowired
    private DateUtils dateUtils;

    //listFilm
    @GetMapping("/listFilm")
    public ModelAndView listFilm(){
        return new ModelAndView("projection/film/list");
    }

    @GetMapping("/checkAvailable")
    public ResponseEntity<Iterable<Film>> checkAvailable(){
        Iterable<Film> films = filmService.findAll();
        filmService.checkAvailable(films);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allFilm")
    public ResponseEntity<Iterable<Film>> listAllFilm(){
        return new ResponseEntity<>(filmService.findAllByOrderByFilm_idDesc(),HttpStatus.OK);
    }

    @PostMapping("/createFilm")
    public ResponseEntity<Film> createFilm(@RequestBody Film film){
        if(film.getExp_date().compareTo(film.getRel_date())< 0 ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(dateUtils.getCurrentDate().compareTo(film.getRel_date()) >0 ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filmService.save(film),HttpStatus.CREATED);
    }

    ///createFilm
    @GetMapping("/createFilm")
    public ModelAndView createFilm(){
        return new ModelAndView("projection/film/create");
    }


    @GetMapping("/edit-form/{id}")
    public ModelAndView getFormEdit(@PathVariable Long id){
        return new ModelAndView("projection/film/edit");
    }

    @GetMapping("/edit-film/{id}")
    public ResponseEntity<Film> productResponseEntity(@PathVariable Long id){
        Film film = filmService.findById(id).get();
        return new ResponseEntity<>(film,HttpStatus.OK);
    }

    @PutMapping("/edit-film/{id}")
    public ResponseEntity<Film> editFilm(@RequestBody Film film,@PathVariable Long id){
        film.setFilm_id(id);
        film.setImage(film.getImage());
        return new ResponseEntity<>(filmService.save(film),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id){
        return new ResponseEntity<>(filmService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/addAdmit/{film_id}")
    public void addAdmit(@PathVariable Long film_id){
        Film film = filmService.findById(film_id).get();
        film.setAdmit(film.getAdmit()+1);
        filmService.save(film);
    }

    @GetMapping("/allTrueFilm")
    public ResponseEntity<Iterable<Film>> allTrueFilm(){
        return new ResponseEntity<>(filmService.findAllByStatusTrue(),HttpStatus.OK);
    }
}
