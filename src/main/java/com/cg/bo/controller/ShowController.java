package com.cg.bo.controller;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Show;
import com.cg.bo.service.impl.RoomServiceImpl;
import com.cg.bo.service.impl.ScheduleServiceImpl;
import com.cg.bo.service.impl.ShowServiceImpl;
import com.cg.bo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private ShowServiceImpl showService;

    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping("/create")
    public ModelAndView showCreateShowForm(){
        return new ModelAndView("/projection/show/create");
    }

    @PostMapping("/create")
    public ResponseEntity<Show> createNewShow(@RequestBody Show show){
        Schedule schedule = scheduleService.findById(show.getSchedule().getSchedule_id()).get();
        if (schedule.getSchedule_date().compareTo(dateUtils.getCurrentDate()) >= 0 ){
            if(java.time.LocalTime.now().compareTo(show.getTime_start().toLocalTime()) > 0){
                show.setStatus(true);
            }
            Iterable<Show> shows = showService.findShowsByRoomName(show.getRoom().getRoom_name(),show.getSchedule().getSchedule_id());
            int count = 0;
            for (Show s :
                    shows) {
                count++;
            }
            if (count == 0){
                return new ResponseEntity<>(showService.save(show), HttpStatus.CREATED);
            } else {
                int check = 0;
                for(Show s :shows){
                    if(show.getTime_end().compareTo(s.getTime_start()) < 0 || show.getTime_start().compareTo(s.getTime_end()) >0){
                        check++;
                    }
                }
                if(check == count){
                    return new ResponseEntity<>(showService.save(show),HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/allShow")
    public ModelAndView listAllShow(){
        return new ModelAndView("/projection/show/list");
    }

    @GetMapping("/apiShow/{scheduleId}")
    public ResponseEntity<Iterable<Show>> listShow(@PathVariable Long scheduleId){
        Schedule schedule = scheduleService.findById(scheduleId).get();
        return new ResponseEntity<>(showService.findShowsBySchedule(schedule),HttpStatus.OK);
    }

    @GetMapping("/allShowsToday/{scheduleId}")
    public ResponseEntity<Iterable<Show>> findAllShowAndSchedule(@PathVariable Long scheduleId){
        Schedule schedule  = scheduleService.findById(scheduleId).get();
        Iterable<Show> shows = showService.findShowsBySchedule(schedule);
        if(dateUtils.getCurrentDate().compareTo(schedule.getSchedule_date()) < 0){
            setStatusShowAfterDate(shows);
        }else {
            setStatusForShow(shows);
        }
        return new ResponseEntity<>(showService.findShowsBySchedule(schedule), HttpStatus.OK);
    }

    @GetMapping("/allShows/{scheduleId}")
    public ResponseEntity<Iterable<Show>> findAllShows(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId).get();
        Iterable<Show> shows = showService.findShowsBySchedule(schedule);
        if (dateUtils.getCurrentDate().compareTo(schedule.getSchedule_date()) < 0) {
            for (Show s :
                    shows) {
                s.setStatus(true);
                showService.save(s);
            }
        } else {
            for (Show s :
                    shows) {
                s.setStatus(false);
                showService.save(s);
            }
        }
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    @GetMapping("/allActiveShowsToday/{scheduleId}")
    public ResponseEntity<Iterable<Show>> findAllShowStatusTrue(@PathVariable Long scheduleId){
        Schedule schedule  = scheduleService.findById(scheduleId).get();
        Iterable<Show> shows = showService.findShowsBySchedule(schedule);
        setStatusForShow(shows);
        return new ResponseEntity<>(showService.findAllByScheduleAndStatusTrue(schedule), HttpStatus.OK);
    }

    public void setStatusForShow(Iterable<Show> shows){
        for (Show s: shows
        ) {
            Time timeStart = s.getTime_start();
            boolean status = 30 >= dateUtils.differentTimeInMinutes(timeStart.toString());
            s.setStatus(status);
            showService.save(s);
        }
    }

    public void setStatusShowAfterDate(Iterable<Show> shows){
        for(Show s :shows){
            s.setStatus(true);
            showService.save(s);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Show> findShowById(@PathVariable Long id){
        Show show = showService.findById(id).get();
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    @GetMapping("/searchShow/{schedule_id}/{film_name}")
    public ResponseEntity<Iterable<Show>> searchByScheduleAndFilmName(@PathVariable Long schedule_id, @PathVariable String film_name){
        Iterable<Show> shows = showService.searchShowOfScheduleWhereShowNameLike(schedule_id,film_name);
        Schedule schedule = scheduleService.findById(schedule_id).get();
        if (dateUtils.getCurrentDate().compareTo(schedule.getSchedule_date()) < 0){
            for (Show s :
                    shows) {
                s.setStatus(true);
                showService.save(s);
            }
        } else if (dateUtils.getCurrentDate().compareTo(schedule.getSchedule_date()) >0){
            for (Show s :
                    shows) {
                s.setStatus(false);
                showService.save(s);
            }
        } else {
            setStatusForShow(shows);
        }
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    @GetMapping("/findShowByRoomId/{roomId}")
    public ResponseEntity<Show> findShowByRoomId(@PathVariable Long roomId){
        Room room = roomService.findById(roomId).get();
        return new ResponseEntity<>(showService.findShowByRoom(room).get(), HttpStatus.OK);
    }
}