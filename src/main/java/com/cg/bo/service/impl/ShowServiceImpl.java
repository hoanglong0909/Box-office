package com.cg.bo.service.impl;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Show;
import com.cg.bo.repository.ShowRepository;
import com.cg.bo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public Iterable<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Optional<Show> findById(Long id) {
        return showRepository.findById(id);
    }

    @Override
    public Show save(Show show) {
        return showRepository.save(show);
    }

    @Override
    public void remove(Long id) {
        showRepository.deleteById(id);
    }


    @Override
    public Iterable<Show> findShowsBySchedule(Schedule schedule) {
        return showRepository.findShowsBySchedule(schedule);
    }

    @Override
    public Iterable<Show> findAllByScheduleAndStatusTrue(Schedule schedule) {
        return showRepository.findAllByScheduleAndStatusTrue(schedule);
    }

    @Override
    public Iterable<Show> findAllByOrderByTime_startAsc() {
        return showRepository.findAllByOrderByTime_startAsc();
    }

    @Override
    public Iterable<Show> searchShowOfScheduleWhereShowNameLike(Long schedule_id, String film_name) {
        return showRepository.searchShowOfScheduleWhereShowNameLike(schedule_id,film_name);
    }

    @Override
    public Iterable<Show> findShowsByRoomName(String room_name, Long id) {
        return showRepository.findShowsByRoomName(room_name,id);
    }

    @Override
    public Optional<Show> findShowByRoom(Room room) {
        return showRepository.findShowByRoom(room);
    }
}